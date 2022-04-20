package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.AssistenceApplication;
import br.com.fiap.abctechservice.application.dto.AssistDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AssistanceControllerTest {

    @Mock
    private AssistenceApplication application;

    private AssistanceController controller;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        controller = new AssistanceController(application);
    }

    @Test
    void should_return_list_and_response_ok() {
        final var assistance = new AssistDto(1L, "Teste", "Teste Description");
        when(application.getAssists()).thenReturn(List.of(assistance));
        final ResponseEntity<List<AssistDto>> response = controller.getAssists();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(assistance, Objects.requireNonNull(response.getBody()).get(0));
    }
}