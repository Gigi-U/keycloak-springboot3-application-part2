package com.dh.msbills.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class KeyCloakJwtAuthenticationConverter implements Converter <Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    private static Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt) throws JsonProcessingException {
        Set<GrantedAuthority> resourcesRoles = new HashSet();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        resourcesRoles.addAll(extractALL("resource_access", objectMapper.readTree(objectMapper.writeValueAsString(jwt)).get("claims")));
        resourcesRoles.addAll(extractALL("realm_access", objectMapper.readTree(objectMapper.writeValueAsString(jwt)).get("claims")));
        resourcesRoles.addAll(extractALL("groups", objectMapper.readTree(objectMapper.writeValueAsString(jwt)).get("claims")));
        resourcesRoles.addAll(extractALL("scope", objectMapper.readTree(objectMapper.writeValueAsString(jwt)).get("claims")));
        return resourcesRoles;
    }
    private static List<GrantedAuthority> extractALL(String route, JsonNode jwt) {

        Set<String> allWithPrefix = new HashSet<>();

        switch (route) {
            case "resource_access":
                jwt
                        .path("resource_access")
                        .elements()
                        .forEachRemaining(e -> e.path("roles")
                                .elements()
                                .forEachRemaining(r -> allWithPrefix.add("ROLE_" + r.asText())));
                 break;

            case "realm_access":
                jwt
                        .path("realm_access")
                        .path("roles")
                        .elements()
                        .forEachRemaining(r -> allWithPrefix.add("ROLE_" + r.asText()));
                break;
            case "groups":
                jwt
                        .path("groups")
                        .elements()
                        .forEachRemaining(r -> allWithPrefix.add("GROUP_" + r.asText()));
                break;
            case "scope":
                jwt
                        .path("scope")
                        .elements()
                        .forEachRemaining(r -> allWithPrefix.add("SCOPE_" + r.asText()));
                break;
            default:
        }
        final List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(allWithPrefix.toArray(new String[0]));

        return authorityList;
    }

    public KeyCloakJwtAuthenticationConverter() {
    }
    @Override
    public AbstractAuthenticationToken convert(final Jwt source) {
        Collection<GrantedAuthority> authorities = null;
        try {
            authorities = this.getGrantedAuthorities(source);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new JwtAuthenticationToken(source, authorities);
    }

    public Collection<GrantedAuthority> getGrantedAuthorities(Jwt source) throws JsonProcessingException {
        return (Collection) Stream.concat(this.defaultGrantedAuthoritiesConverter.convert(source).stream(), extractResourceRoles(source).stream()).collect(Collectors.toSet());
    }
}
