package com.ticket;

import com.ticket.Models.UserRegistrationRecord;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;


public interface KeycloakUserService {


    ResponseEntity<String> createUser(UserRegistrationRecord userRegistrationRecord);

    UserRepresentation getUserById(String userId);
    void deleteUserById(String userId);

}
