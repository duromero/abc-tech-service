package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.handler.exception.UserNotFound;
import br.com.fiap.abctechservice.repository.UserRepository;
import br.com.fiap.abctechservice.util.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ApplicationUserDetailsServiceTest {
    @Mock
    private UserRepository repository;

    private ApplicationUserDetailsService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        service = new ApplicationUserDetailsService(repository);
    }

    @Test
    public void shouldLoadUserByUsernameWhenUsernameExists() {
        final var username = "username";
        when(repository.findByLogin(username)).thenReturn(Optional.of(Users.getValidUser()));
        final var userDetails = service.loadUserByUsername(username);
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(0, userDetails.getAuthorities().size());
        verify(repository, times(1)).findByLogin(username);
    }

    @Test
    public void shouldThrowExceptionWhenUsernameDoesNotExist() {
        final var username = "username";
        when(repository.findByLogin(username)).thenReturn(Optional.empty());
        assertThrows(UserNotFound.class, () -> service.loadUserByUsername(username));
        verify(repository, times(1)).findByLogin(username);
    }
}