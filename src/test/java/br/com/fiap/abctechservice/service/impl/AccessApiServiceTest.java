package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.handler.exception.UserNotFound;
import br.com.fiap.abctechservice.repository.UserRepository;
import br.com.fiap.abctechservice.util.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccessApiServiceTest {
    @Mock
    private UserRepository repository;

    private AccessApiService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        service = new AccessApiService(repository);
    }

    @Test
    public void shouldLoadUserByUsernameWhenUsernameExists() {
        final var userId = 1L;
        when(repository.findById(userId)).thenReturn(Optional.of(Users.getValidUser()));
        final var usuario = service.getUserAcesso(userId);
        assertNotNull(usuario);
        assertEquals("username", usuario.getUsername());
        assertEquals(0, usuario.getAuthorities().size());
        verify(repository, times(1)).findById(userId);
    }

    @Test
    public void shouldThrowExceptionWhenUsernameDoesNotExist() {
        final var userId = 1L;
        when(repository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFound.class, () -> service.getUserAcesso(userId));
        verify(repository, times(1)).findById(userId);
    }

}