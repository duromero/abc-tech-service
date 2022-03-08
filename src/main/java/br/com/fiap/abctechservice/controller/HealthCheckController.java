package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheckController {

    @Autowired
    private HealthCheckService healthCheckService;

    @GetMapping()
    public ResponseEntity<Object> status(){
        return ResponseEntity.ok("Sucesso");
    }

    @GetMapping("version")
    public ResponseEntity<String> version(){
        return ResponseEntity.ok(healthCheckService.getVersion());
    }
}
