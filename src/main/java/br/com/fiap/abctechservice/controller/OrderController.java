package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.OrderApplication;
import br.com.fiap.abctechservice.application.dto.OrderDto;
import br.com.fiap.abctechservice.application.impl.responseDTO.ResponseOrderDTO;
import br.com.fiap.abctechservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderApplication orderApplication;

    public OrderController (@Autowired OrderApplication orderApplication){
        this.orderApplication = orderApplication;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrderDTO> getOrder(@PathVariable("id") String id){
        ResponseOrderDTO order = this.orderApplication.getOrder(Long.parseLong(id));
        return ResponseEntity.ok(order);
    }

    @PostMapping()
    public ResponseEntity<Order> saveOrder(@Valid @RequestBody OrderDto orderDto){
        final Order created = orderApplication.createOrder(orderDto);
        return ResponseEntity.created(buildUri(created)).body(created);
    }

    private URI buildUri(Order order) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(order.getId())
            .toUri();
    }
}
