package br.com.fiap.abctechservice.application;

import br.com.fiap.abctechservice.application.dto.TokenDto;
import br.com.fiap.abctechservice.application.form.LoginForm;

import javax.servlet.http.HttpServletRequest;

public interface AuthApplication {

    public TokenDto autenticar(LoginForm user, HttpServletRequest request);
}
