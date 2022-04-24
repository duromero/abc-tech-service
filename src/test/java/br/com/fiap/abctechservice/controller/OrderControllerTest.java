package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.OrderApplication;
import br.com.fiap.abctechservice.application.dto.AssistDto;
import br.com.fiap.abctechservice.application.dto.OrderDto;
import br.com.fiap.abctechservice.application.dto.OrderLocationDto;
import br.com.fiap.abctechservice.application.impl.responseDTO.ResponseOrderDTO;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.model.OrderLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderControllerTest {

    @Mock
    private OrderApplication application;

    private OrderController controller;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        controller = new OrderController(application);
    }

    @Test
    void should_return_order_and_response_ok() {
        final var id = Long.valueOf(123L);
        final var order = new ResponseOrderDTO(123L, Collections.emptyList(), new OrderLocationDto(), new OrderLocationDto());
        when(application.getOrder(id)).thenReturn(order);
        final ResponseEntity<ResponseOrderDTO> response = controller.getOrder(id.toString());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(order, Objects.requireNonNull(response.getBody()));
    }

    @Test
    void should_create_order_and_return_response_created() {
        final var id = Long.valueOf(123L);
        final var order = new OrderDto(123L, Collections.emptyList(), new OrderLocationDto(), new OrderLocationDto());
        final var created = new Order(id, 123L, Collections.emptyList(), new OrderLocation(), new OrderLocation());
        when(application.createOrder(order)).thenReturn(created);
        final ResponseEntity<Order> response = controller.saveOrder(order);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(created, Objects.requireNonNull(response.getBody()));
    }
}