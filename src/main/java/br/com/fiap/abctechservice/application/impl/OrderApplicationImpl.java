package br.com.fiap.abctechservice.application.impl;

import br.com.fiap.abctechservice.application.OrderApplication;
import br.com.fiap.abctechservice.application.dto.AssistDto;
import br.com.fiap.abctechservice.application.dto.OrderDto;
import br.com.fiap.abctechservice.application.dto.OrderLocationDto;
import br.com.fiap.abctechservice.application.impl.responseDTO.ResponseOrderDTO;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.model.OrderLocation;
import br.com.fiap.abctechservice.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderApplicationImpl implements OrderApplication {

    private final OrderServiceImpl service;

    public OrderApplicationImpl(@Autowired  OrderServiceImpl service) {
        this.service = service;
    }

    @Override
    public Order createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOperatorId(orderDto.getOperatorId());
        order.setStartOrderLocation(getOrderLocationFromOrderLocationDto(orderDto.getStart()));
        order.setEndtOrderLocation(getOrderLocationFromOrderLocationDto(orderDto.getEnd()));

        return service.saveOrder(order, orderDto.getServices());
    }

    @Override
    public ResponseOrderDTO getOrder(Long id) {
        Order order = this.service.getOrder(id);
        ResponseOrderDTO orderDto= new ResponseOrderDTO();
        orderDto.setOperatorId(order.getOperatorId());
        List<AssistDto> assistDtoList = order.getServices().stream().map(assistance -> new AssistDto(assistance.getId(), assistance.getName(),
                assistance.getDescription())).collect(Collectors.toList());
        orderDto.setServices(assistDtoList);
        orderDto.setStart(getOrderLocationDtoFromOrderLocation(order.getStartOrderLocation()));
        orderDto.setEnd(getOrderLocationDtoFromOrderLocation(order.getEndtOrderLocation()));

        return orderDto;
    }

    private OrderLocationDto getOrderLocationDtoFromOrderLocation(OrderLocation orderLocation) {
        OrderLocationDto location = new OrderLocationDto();
        location.setLatitude(orderLocation.getLatitude());
        location.setLongitude(orderLocation.getLongitude());
        location.setDateTime(orderLocation.getDate());

        return location;
    }

    private OrderLocation getOrderLocationFromOrderLocationDto(OrderLocationDto orderLocationDto) {
        OrderLocation location = new OrderLocation();
        location.setLatitude(orderLocationDto.getLatitude());
        location.setLongitude(orderLocationDto.getLongitude());
        location.setDate(orderLocationDto.getDateTime());

        return location;
    }

}
