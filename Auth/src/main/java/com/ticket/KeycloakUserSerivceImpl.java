package com.ticket;

import com.ticket.Models.UserLoginRecord;
import com.ticket.Models.UserRegistrationRecord;
import com.ticket.Models.userLoginResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Service;

import org.keycloak.admin.client.resource.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class KeycloakUserSerivceImpl implements KeycloakUserService {

    private static final String BASE_URL = "http://localhost:8088/realms/Ticket/protocol/openid-connect/token";

    @Autowired
    private RestTemplate restTemplate;

    private  Keycloak keycloak;

//    private String keycloakRealm;

    // The RestTemplate object to make HTTP requests

    // Constructor injection of the RestTemplate


    // A method that takes a username and password and returns a Ticket object
    public String getUserInfo(UserLoginRecord userLoginRecord) {

        // Create a map of request parameters


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.put("username", Collections.singletonList(userLoginRecord.username()));
        params.put("password", Collections.singletonList(userLoginRecord.password()));
        params.put("client_secret", Collections.singletonList("5VOimCUrlleKMlDT6PAbUCtI3iqpc4nX"));
        params.put("grant_type", Collections.singletonList("password"));
        params.put("client_id", Collections.singletonList("Ticket-App"));

        // Create a HttpEntity object with the parameters and headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<userLoginResponse> response = restTemplate.exchange(BASE_URL, HttpMethod.POST, request, userLoginResponse.class);

        // Call the API using the exchange method and get the response as a Ticket object
        // Get the userLoginResponse object from the response
        userLoginResponse userLogin = response.getBody();

// Get the token and refresh token from the userLoginResponse object
        String token = userLogin.getAccess_token();
        String refreshToken = userLogin.getRefresh_token();

// Display the token and refresh token
        System.out.println("Token: " + token);
        System.out.println("Refresh Token: " + refreshToken);
            return  token;
    }

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
