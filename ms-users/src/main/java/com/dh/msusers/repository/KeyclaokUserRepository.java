package com.dh.msusers.repository;

import com.dh.msusers.model.User;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class KeyclaokUserRepository implements IUserRepository{

    private final Keycloak keycloakClient;
    @Value("${dh.keycloak.realm}")
    private String realm;

    private User toUser(UserRepresentation userRepresentation) {
        return User.builder()
                .id(userRepresentation.getId())
                .userName(userRepresentation.getUsername())
                .email(userRepresentation.getEmail())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .build();
    }

    @Override
    public Optional<User>findById(String id) {

        UserRepresentation userRepresentation;

        try {
            userRepresentation = keycloakClient.realm(realm)
                    .users().get(id)
                    .toRepresentation();
        } catch (NotFoundException e) { return Optional.empty(); }

        return Optional.of(toUser(userRepresentation));

    }

}
