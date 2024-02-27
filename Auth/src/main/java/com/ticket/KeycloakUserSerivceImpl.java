package com.ticket;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.keycloak.admin.client.resource.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class KeycloakUserSerivceImpl implements KeycloakUserService {

    private  Keycloak keycloak;

//    private String keycloakRealm;


    @Override
    public UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord) {

        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userRegistrationRecord.username());
        user.setEmail(userRegistrationRecord.email());
        user.setFirstName(userRegistrationRecord.firstName());
        user.setLastName(userRegistrationRecord.lastName());
        user.setEmailVerified(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(userRegistrationRecord.password());

        List<CredentialRepresentation> credentials = new ArrayList<>();
        credentials.add(credential);

        user.setCredentials(credentials);


     //  RealmResource realm1 = keycloak.realm(keycloakRealm);
        UsersResource userResource = getUsersResource();

        userResource.create(user);

        return userRegistrationRecord;
    }

    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm("Ticket");
        return realm1.users();
    }


    @Override
    public UserRepresentation getUserById(String userId) {
//        return  getUsersResource().get(userId).toRepresentation();
        return  getUsersResource().get(userId).toRepresentation();
    }

    @Override
    public void deleteUserById(String userId) {

        getUsersResource().delete(userId);
    }



//    @Override
//    public void emailVerification(String userId){
//
//        UsersResource usersResource = getUsersResource();
//        usersResource.get(userId).sendVerifyEmail();
//    }
//
//    public UserResource getUserResource(String userId){
//        UsersResource usersResource = getUsersResource();
//        return usersResource.get(userId);
//    }
//
//    @Override
//    public void updatePassword(String userId) {
//
//        UserResource userResource = getUserResource(userId);
//        List<String> actions= new ArrayList<>();
//        actions.add("UPDATE_PASSWORD");
//        userResource.executeActionsEmail(actions);
//
//    }



}
