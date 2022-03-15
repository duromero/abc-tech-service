package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.repository.AssistanceRepository;
import br.com.fiap.abctechservice.service.impl.AssistanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assistance")
public class AssistanceController {

    private final AssistanceServiceImpl service;

    public AssistanceController (@Autowired AssistanceServiceImpl service){
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Assistance>> getAssists(){
        List<Assistance> list = this.service.getAssistList();
        return  ResponseEntity.ok(list);
    }
}
