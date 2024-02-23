package com.ticket;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

// any one can access it if it dons not have a PreAuthorize
    @GetMapping("/login")
    @PreAuthorize("permitAll()")
    public String login() {
        return "Login";
    }

    @GetMapping("/register")
    @PreAuthorize("hasRole('ROLE_client-user')")
    public String register() {
        return "Register";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ROLE_client-admin')")
    public String delete() {
        return "delete";
    }

}
