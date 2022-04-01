package br.com.fiap.abctechservice.application.impl;

import br.com.fiap.abctechservice.application.OrderApplication;
import br.com.fiap.abctechservice.application.dto.OrderDto;
import br.com.fiap.abctechservice.application.dto.OrderLocationDto;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.model.OrderLocation;
import br.com.fiap.abctechservice.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderApplicationImpl implements OrderApplication {

    private final OrderServiceImpl service;

    public OrderApplicationImpl(@Autowired  OrderServiceImpl service) {
        this.service = service;
    }

    @Override
    public void createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOperatorId(orderDto.getOperatorId());
        order.setStartOrderLocation(getOrderLocationFromOrderLocationDto(orderDto.getStart()));
        order.setEndtOrderLocation(getOrderLocationFromOrderLocationDto(orderDto.getEnd()));

        this.service.saveOrder(order, orderDto.getServices());
    }

    private OrderLocation getOrderLocationFromOrderLocationDto(OrderLocationDto orderLocationDto) {
        OrderLocation location = new OrderLocation();
        location.setLatitude(orderLocationDto.getLatitude());
        location.setLongitude(orderLocationDto.getLongitude());
        location.setDate(orderLocationDto.getDateTime());

        return location;
    }

}
