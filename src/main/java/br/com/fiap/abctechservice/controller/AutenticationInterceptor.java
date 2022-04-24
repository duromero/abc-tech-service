package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.model.Usuario;
import br.com.fiap.abctechservice.service.impl.AccessApiService;
import br.com.fiap.abctechservice.util.ConstHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticationInterceptor extends OncePerRequestFilter {

    private AccessApiService acessoApiService;

    public AutenticationInterceptor(@Autowired AccessApiService acessoApiService) {
        this.acessoApiService = acessoApiService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain fc) throws ServletException, IOException {

        String token = recuperarToken(request);
        String ipRequisicao = request.getRemoteAddr();
        String ipServer = request.getLocalAddr();

        boolean valido = acessoApiService.isTokenValido(token, ipRequisicao, ipServer);

        if (valido) {
            autenticarCliente(token);
        }

        fc.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader(ConstHelper.HEADER_NAME);

        if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
            return null;
        } else {
            return token.substring(7, token.length());
        }

    }

    private void autenticarCliente(String token) {
        Long idToken = acessoApiService.getIdUserAcesso(token);
        Usuario userAcesso = acessoApiService.getUserAcesso(idToken);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userAcesso, null, userAcesso.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
