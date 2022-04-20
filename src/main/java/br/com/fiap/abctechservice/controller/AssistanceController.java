package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.AssistenceApplication;
import br.com.fiap.abctechservice.application.dto.AssistDto;
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

    private final AssistenceApplication assistenceApplication;

    public AssistanceController (@Autowired AssistenceApplication assistenceApplication){
        this.assistenceApplication = assistenceApplication;
    }

    @GetMapping()
    public ResponseEntity<List<AssistDto>> getAssists(){
        List<AssistDto> list = this.assistenceApplication.getAssists();
        return ResponseEntity.ok(list);
    }
}
