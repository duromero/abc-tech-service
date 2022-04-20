package br.com.fiap.abctechservice.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderTest {

    @Test
    void should_return_true_if_services_list_is_greater_than_zero() {
        final var assistance = new Assistance(1L, "Teste", "Teste Description");
        var order = new Order(123L, 123L, List.of(assistance), new OrderLocation(), new OrderLocation());
        assertTrue(order.hasMinAssists());
    }

    @Test
    void should_return_false_if_services_list_is_empty() {
        var order = new Order(123L, 123L, Collections.emptyList(), new OrderLocation(), new OrderLocation());
        assertFalse(order.hasMinAssists());
    }

    @Test
    void should_return_true_if_services_list_is_greater_than_fifteen() {
        List<Assistance> assists = new ArrayList<>(16);
        for(long i = 0; i < 16; i++){
            final var assistance = new Assistance(i, "Teste", "Teste Description");
            assists.add(assistance);
        }
        var order = new Order(123L, 123L, assists, new OrderLocation(), new OrderLocation());
        assertTrue(order.exceedsMaxAssists());
    }

    @Test
    void should_return_false_if_services_list_is_less_than_fifteen() {
        List<Assistance> assists = new ArrayList<>(15);
        for(long i = 0; i < 15; i++){
            final var assistance = new Assistance(i, "Teste", "Teste Description");
            assists.add(assistance);
        }
        var order = new Order(123L, 123L, assists, new OrderLocation(), new OrderLocation());
        assertFalse(order.exceedsMaxAssists());
    }
}