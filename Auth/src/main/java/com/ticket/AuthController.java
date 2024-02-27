package com.ticket;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Slf4j
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

        // this is a public route
    @Autowired
    private  KeycloakUserSerivceImpl keycloakUserService1;

// any one can access it if it dons not have a PreAuthorize
    @GetMapping("/login")
    @PreAuthorize("permitAll()")
    public String login() {
        return "Login";
    }

    @PostMapping("/register")
    public UserRegistrationRecord register(@RequestBody UserRegistrationRecord userRegistrationRecord) {

        return  keycloakUserService1.createUser(userRegistrationRecord);

   }

   @GetMapping("/getUser")
   public UserRepresentation getUser(Principal principal) {
        log.info("principal name: {}", principal.getName());
       return keycloakUserService1.getUserById(principal.getName());
   }

    @DeleteMapping("/delete/{userId}")
//    @PreAuthorize("hasRole('ROLE_client-admin')")
    public String deleteById( @PathVariable String userId) {
         keycloakUserService1.deleteUserById(userId);

        return " user deleted";
    }

}
