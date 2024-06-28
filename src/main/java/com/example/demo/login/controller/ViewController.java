package com.example.demo.login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.login.auth.AuthenticationFacade;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final AuthenticationFacade authenticationFacade;

    @GetMapping("/")
    public String root() {
        Authentication authentication = authenticationFacade.getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/oauth2/authorization/azure";
        }
        return "redirect:/home";  // Redirect to home if authenticated
    }

    @GetMapping("/home")
    public String viewHomePage(Model model) {
        model.addAttribute("email", authenticationFacade.getUserEmail()); 
        return "home";
    }


}
