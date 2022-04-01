package br.com.fiap.abctechservice.service;

import br.com.fiap.abctechservice.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrderList();

    Order getOrder(Long id);

    void saveOrder(Order order, List<Long> arrayAssists) throws Exception;

    List<Order> listOrdersByOperator(Long operatorId);
}
