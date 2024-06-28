package com.example.demo.login.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@EnableConfigurationProperties
@ConfigurationProperties("demo.logout")
@Getter
@Setter
public class LogoutProperties {
    
    private String tenantId;
    private String redirectUri;
}
