package com.example.demo.login.auth;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.example.demo.login.config.LogoutProperties;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ServerSecurityConfiguration {

    private final LogoutProperties logoutProperties;

    @Bean
    public SecurityFilterChain authSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector)
            throws Exception {
        MvcRequestMatcher.Builder builder = new MvcRequestMatcher.Builder(introspector);

        http
            .cors(cors -> cors.disable())
            .csrf(csrf -> csrf.disable())
            .headers(headersConfigurer -> headersConfigurer
                    .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))
            .authorizeHttpRequests(authorize -> {
                authorize
                        .requestMatchers(builder.pattern("/oauth2/authorization/azure")).permitAll()
                        .anyRequest().authenticated();

            })
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/oauth2/authorization/azure/whatisthis")
                .defaultSuccessUrl("/home", true)
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")
                .logoutSuccessHandler((request, response, authentication) -> {
                    String redirectUri = URLEncoder.encode("http://localhost:8080", StandardCharsets.UTF_8);
                    response.sendRedirect("https://login.microsoftonline.com/" + logoutProperties.getTenantId() + "/oauth2/logout?post_logout_redirect_uri=" + redirectUri);
                })
            );

        return http.build();
    }

        // @Bean
        // public CorsConfigurationSource corsConfigurationSource() {
        //         CorsConfiguration configuration = new CorsConfiguration();
        //         configuration.addAllowedOriginPattern("*");
        //         // configuration.setAllowedOrigins("*");
        //         configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS"));
        //         configuration.setAllowedHeaders(List.of("*"));
        //         configuration.setExposedHeaders(List.of("*"));
        //         configuration.setAllowCredentials(true);

        //         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //         source.registerCorsConfiguration("/**", configuration); // Apply this configuration to all
        //                                                                 // endpoints
        //         return source;
        // }
}
