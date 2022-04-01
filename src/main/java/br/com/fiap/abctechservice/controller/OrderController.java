package br.com.fiap.abctechservice.controller;


import br.com.fiap.abctechservice.application.OrderApplication;
import br.com.fiap.abctechservice.application.dto.OrderDto;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderApplication orderApplication;

    public OrderController (@Autowired OrderApplication orderApplication){
        this.orderApplication = orderApplication;
    }


//    @GetMapping()
//    public ResponseEntity<List<Order>> getOrder(){
//        List<Order> list = this.service.getOrderList();
//        return  ResponseEntity.ok(list);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Order> getOrder(@PathVariable("id") String id){
//        System.out.println(id);
//        Order order = this.service.getOrder(Long.parseLong(id));
//        return  ResponseEntity.ok(order);
//    }

    @PostMapping()
    public ResponseEntity<Order> saveOrder( @Valid @RequestBody OrderDto orderDto){
        this.orderApplication.createOrder(orderDto);
        return  ResponseEntity.ok().build();
    }
}
