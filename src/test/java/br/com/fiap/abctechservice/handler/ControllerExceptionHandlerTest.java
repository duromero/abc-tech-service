package br.com.fiap.abctechservice.handler;

import br.com.fiap.abctechservice.handler.exception.CustomException;
import br.com.fiap.abctechservice.handler.exception.MaxAssistsException;
import br.com.fiap.abctechservice.handler.exception.MinimumAssistsRequiredException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExceptionHandlerTest {

    private final ControllerExceptionHandler handler = new ControllerExceptionHandler();

    @Test
    public void should_return_bad_request_for_custom_exception() {
        final var exception = new CustomException(400, "Argumento Inválido", "O argumento [XXX] não é válido");
        final ResponseEntity<ErrorMessageResponse> response = handler.errorCustomException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Argumento Inválido", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals("O argumento [XXX] não é válido", Objects.requireNonNull(response.getBody()).getDescription());
    }

    @Test
    public void should_return_not_found_for_custom_exception() {
        final var exception = new CustomException(404, "Recurso não encontrado", "O recurso [XXX] não foi encontrado");
        final ResponseEntity<ErrorMessageResponse> response = handler.errorCustomException(exception);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Recurso não encontrado", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals("O recurso [XXX] não foi encontrado", Objects.requireNonNull(response.getBody()).getDescription());
    }

    @Test
    public void should_return_bad_request_for_min_assists_exception() {
        final var exception = new MinimumAssistsRequiredException(
            "Número de assistências inválido",
            "O número de assistências relacionadas a essa ordem é menor que o mínimo necessário"
        );
        final ResponseEntity<ErrorMessageResponse> response = handler.errorMinAssistRequired(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Número de assistências inválido", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals("O número de assistências relacionadas a essa ordem é menor que o mínimo necessário",
            Objects.requireNonNull(response.getBody()).getDescription());
    }

    @Test
    public void should_return_bad_request_for_max_assists_exception() {
        final var exception = new MaxAssistsException(
            "Número de assistências inválido",
            "O número de assistências relacionadas a essa ordem é maior que o máximo permitido"
        );
        final ResponseEntity<ErrorMessageResponse> response = handler.errorMaxAssisException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Número de assistências inválido", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals("O número de assistências relacionadas a essa ordem é maior que o máximo permitido",
            Objects.requireNonNull(response.getBody()).getDescription());
    }
}