// package com.example.demo.login.controller;

// import java.io.IOException;
// import java.net.URLEncoder;
// import java.nio.charset.StandardCharsets;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.login.auth.AuthenticationFacade;
// import com.example.demo.login.config.LogoutProperties;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;

// @RestController
// @RequiredArgsConstructor
// public class LogoutController {

//     private final LogoutProperties logoutProperties;

//     private final AuthenticationFacade authenticationFacade;
//     private final HttpServletResponse httpResponse;
    
//     @GetMapping("/perform_logout")
//     public String performLogout(HttpServletRequest request, HttpServletResponse response) {
//         Authentication auth = authenticationFacade.getAuthentication();
//         if (auth != null) {
//             new SecurityContextLogoutHandler().logout(request, response, auth);
//         }
//         // Redirect to Azure AD logout URL
//         // try {
//         //     httpResponse.sendRedirect("https://login.microsoftonline.com/" + logoutProperties.getTenantId() + "/oauth2/logout?post_logout_redirect_uri=" + logoutProperties.getRedirectUri());
//         // } catch (IOException e) {
//         //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//         // }

//         try {
//             httpResponse.sendRedirect("http://localhost:8080");
//         } catch (IOException e) {
//             return "error";
//         }
        
//         return "redirect:/";
//     }
// }
