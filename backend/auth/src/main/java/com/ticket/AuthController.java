package com.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ticket.Models.UserInfo;
import com.ticket.Models.UserLoginRecord;
import com.ticket.Models.UserRegistrationRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @CrossOrigin(origins = "*")

    @PostMapping("/login")
   @PreAuthorize("permitAll()")
    public Object login(@RequestBody UserLoginRecord  userLoginRecord) {
        log.info("userLoginRecord: {}", userLoginRecord.toString() );
         return        keycloakUserService1.getUserTokens(userLoginRecord);

    }
    @CrossOrigin(origins = "*")

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationRecord userRegistrationRecord) {
        return  keycloakUserService1.createUser(userRegistrationRecord);
   }

   @GetMapping("/getUser")
   @PreAuthorize("permitAll()")

   public UserInfo getUser(@RequestBody String token) throws JsonProcessingException {

        log.info("token :{}", token);
       return keycloakUserService1.getUserInfo(token);

   }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String deleteById( @PathVariable String userId) {
         keycloakUserService1.deleteUserById(userId);

        return " user deleted";

    }

//    @GetMapping("/Verfiy")
//    public void verfiyUser(Principal principal) {
//        log.info("principal name: {}", principal.getName());
//         keycloakUserService1.emailVerification(principal.getName());
//    }


}
