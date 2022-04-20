package br.com.fiap.abctechservice.service;

import br.com.fiap.abctechservice.handler.exception.MaxAssistsException;
import br.com.fiap.abctechservice.handler.exception.MinimumAssistsRequiredException;
import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.model.OrderLocation;
import br.com.fiap.abctechservice.repository.AssistanceRepository;
import br.com.fiap.abctechservice.repository.OrderRepository;
import br.com.fiap.abctechservice.service.impl.OrderServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private AssistanceRepository assistanceRepository;
    private OrderService orderService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        orderService = new OrderServiceImpl(orderRepository, assistanceRepository);
        when(assistanceRepository.findById(any())).thenReturn(Optional.of(new Assistance(1L, "Teste", "Teste Description")));
    }

    @Test
    public void order_service_not_null(){
        Assertions.assertNotNull(orderService);
    }

    @Test
    public void create_order_success() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        orderService.saveOrder(newOrder, generate_mock_assistance(1));

        verify(orderRepository, times(1)).save(newOrder);
    }

    @Test
    public void create_order_error_minimum() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        assertThrows(MinimumAssistsRequiredException.class, () -> orderService.saveOrder(newOrder, List.of()));
        verify(orderRepository, times(0)).save(newOrder);
    }

    @Test
    public void create_order_error_maximum() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        assertThrows(MaxAssistsException.class, () -> orderService.saveOrder(newOrder, generate_mock_assistance(20)));
        verify(orderRepository, times(0)).save(newOrder);
    }

    @Test
    public void should_throw_exception_when_operatorId_is_null() {
        assertThrows(IllegalArgumentException.class, () -> orderService.listOrdersByOperator(null));
    }

    @Test
    public void should_throw_exception_when_operatorId_is_negative() {
        assertThrows(IllegalArgumentException.class, () -> orderService.listOrdersByOperator(-1L));
    }

    @Test
    public void should_get_list_when_operatorId_is_valid() {
        var operatorId = 1L;
        var order = new Order(1L, operatorId, Collections.emptyList(), new OrderLocation(), new OrderLocation());
        when(orderRepository.findAllOrderByOperatorId(operatorId))
            .thenReturn(List.of(order));

        final List<Order> orders = orderService.listOrdersByOperator(operatorId);

        assertEquals(1, orders.size());
        assertEquals(1L, orders.get(0).getOperatorId());
    }

    private List<Long> generate_mock_assistance(int number) {
        ArrayList<Long> arrayList = new ArrayList<>();
        for (int x = 0; x < number; x++){
            arrayList.add(Integer.toUnsignedLong(x));
        }
        return  arrayList;
    }
}
