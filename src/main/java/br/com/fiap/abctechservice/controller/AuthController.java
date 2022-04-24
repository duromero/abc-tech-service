package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.AuthApplication;
import br.com.fiap.abctechservice.application.dto.TokenDto;
import br.com.fiap.abctechservice.application.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthApplication authApplication;

    public AuthController (@Autowired AuthApplication authApplication){
        this.authApplication = authApplication;
    }

    @PostMapping()
    public ResponseEntity<TokenDto> autenticar(@RequestBody LoginForm user, HttpServletRequest request) {
            return ResponseEntity.ok(this.authApplication.autenticar(user,request));
    }
}
