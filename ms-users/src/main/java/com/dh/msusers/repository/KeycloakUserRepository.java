package com.dh.msusers.repository;

import com.dh.msusers.model.User;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class KeycloakUserRepository implements IUserRepository{

    private final Keycloak keycloakClient;
    @Value("${dh.keycloak.realm}")
    private String realm;

    public KeycloakUserRepository(Keycloak keycloakClient) {
        this.keycloakClient = keycloakClient;
    }

    private User toUser(UserRepresentation userRepresentation) {
        return new User(
                userRepresentation.getId(),
                userRepresentation.getUsername(),
                userRepresentation.getEmail(),
                userRepresentation.getFirstName(),
                userRepresentation.getLastName());
    }

    @Override
    public Optional<User>findById(String id) {
        UserRepresentation userRepresentation = keycloakClient.realm(realm)
                    .users().get(id)
                    .toRepresentation();
        return Optional.of(toUser(userRepresentation));

    }

}
