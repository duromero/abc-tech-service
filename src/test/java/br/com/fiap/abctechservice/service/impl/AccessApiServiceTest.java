package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.handler.exception.UserNotFound;
import br.com.fiap.abctechservice.repository.UserRepository;
import br.com.fiap.abctechservice.util.Users;
import io.jsonwebtoken.ExpiredJwtException;
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
    public void shouldLoadUserByUserIdWhenIdExists() {
        final var userId = 1L;
        when(repository.findById(userId)).thenReturn(Optional.of(Users.getValidUser()));
        final var usuario = service.getUserAcesso(userId);
        assertNotNull(usuario);
        assertEquals("username", usuario.getUsername());
        assertEquals(0, usuario.getAuthorities().size());
        verify(repository, times(1)).findById(userId);
    }

    @Test
    public void shouldThrowExceptionWhenIdDoesNotExist() {
        final var userId = 1L;
        when(repository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFound.class, () -> service.getUserAcesso(userId));
        verify(repository, times(1)).findById(userId);
    }

    @Test
    public void shouldReturnTrueWhenLoginExists() {
        final var login = "login";
        when(repository.findByLogin(login)).thenReturn(Optional.of(Users.getValidUser()));
        final var tokenValido = service.verificarToken(login);
        assertTrue(tokenValido);
        verify(repository, times(1)).findByLogin(login);
    }

    @Test
    public void shouldThrowExceptionWhenLoginDoesNotExist() {
        final var login = "login";
        when(repository.findByLogin(login)).thenReturn(Optional.empty());
        assertThrows(UserNotFound.class, () -> service.verificarToken(login));
        verify(repository, times(1)).findByLogin(login);
    }

    @Test
    public void shouldThrowExceptionWhenTokenIsExpired() {
        assertThrows(ExpiredJwtException.class, () -> service.getIdUserAcesso(Users.EXPIRED_TOKEN));
    }

}