package com.ticket;

import com.ticket.Models.UserRegistrationRecord;
import org.keycloak.representations.idm.UserRepresentation;


public interface KeycloakUserService {


    UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord);

    UserRepresentation getUserById(String userId);
    void deleteUserById(String userId);

}
