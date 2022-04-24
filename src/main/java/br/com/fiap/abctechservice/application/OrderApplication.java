package br.com.fiap.abctechservice.application;

import br.com.fiap.abctechservice.application.dto.OrderDto;
import br.com.fiap.abctechservice.application.impl.responseDTO.ResponseOrderDTO;
import br.com.fiap.abctechservice.model.Order;

public interface OrderApplication {

    Order createOrder (OrderDto orderDto);

    ResponseOrderDTO getOrder(Long id);
}
