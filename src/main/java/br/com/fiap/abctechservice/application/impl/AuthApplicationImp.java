package br.com.fiap.abctechservice.application.impl;

import br.com.fiap.abctechservice.application.AuthApplication;
import br.com.fiap.abctechservice.application.dto.TokenDto;
import br.com.fiap.abctechservice.application.form.LoginForm;
import br.com.fiap.abctechservice.handler.exception.UserNotFound;
import br.com.fiap.abctechservice.service.impl.AccessApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthApplicationImp implements AuthApplication {

    private final AccessApiService acessoApiService;
    private final AuthenticationManager authManager;

    public AuthApplicationImp(AccessApiService acessoApiService, AuthenticationManager authManager) {
        this.acessoApiService = acessoApiService;
        this.authManager = authManager;
    }

    @Override
    public TokenDto autenticar(LoginForm user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken dadosLogin = user.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = this.acessoApiService.gerarTokenJwt(authentication, request.getRemoteAddr());
            return new TokenDto(token,"Bearer");
        } catch (AuthenticationException ex) {
            throw new UserNotFound("Usuário ou senha inválidos");
        }
    }
}
