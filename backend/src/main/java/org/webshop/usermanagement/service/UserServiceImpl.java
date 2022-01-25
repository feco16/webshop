package org.webshop.usermanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.webshop.common.security.KeycloakAdminClientService;
import org.webshop.to.CreateUserTO;
import org.webshop.usermanagement.model.AppUser;
import org.webshop.usermanagement.model.UserConverter;
import org.webshop.usermanagement.repository.WriteUserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final KeycloakAdminClientService keycloakAdminClientService;
    private final UserConverter userConverter;
    private final WriteUserRepository userRepository;

    @Override
    @Transactional
    public void createUser(final CreateUserTO createUserTO) {
        final AppUser appUser = userConverter.convert(createUserTO);
        userRepository.save(appUser);
        final int statusCode = keycloakAdminClientService.addUser(createUserTO);
        if (statusCode == HttpStatus.CONFLICT.value()) {
            log.error("User already exists in keycloak. Error code {}", statusCode);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Keycloak error");
        }
        if (statusCode != HttpStatus.CREATED.value()) {
            log.error("Cannot create keycloak user. Error code {}", statusCode);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Keycloak error");
        }
    }
}
