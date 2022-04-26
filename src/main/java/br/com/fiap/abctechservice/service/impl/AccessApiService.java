package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.handler.exception.UserNotFound;
import br.com.fiap.abctechservice.model.Usuario;
import br.com.fiap.abctechservice.repository.UserRepository;
import br.com.fiap.abctechservice.security.UserSystem;
import br.com.fiap.abctechservice.util.ConstHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.apache.commons.codec.digest.DigestUtils;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AccessApiService {

    private final UserRepository userRepository;

    public AccessApiService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usuario getUserAcesso(Long id) {
        Optional<Usuario> user = this.userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFound(id.toString(), true);
        }

        return user.get();
    }

    public Long getIdUserAcesso(String token) {
        Claims corpo = this.getCorpoToken(token);
        return Long.parseLong(corpo.getSubject());
    }

    private Claims getCorpoToken(String token) {
        return Jwts.parser().setSigningKey(ConstHelper.KEY).parseClaimsJws(token).getBody();
    }

    public boolean verificarToken(String login) {
        return this.checarLogin(login);
    }

    private boolean checarLogin(String login) {

        Optional<Usuario> user = this.userRepository.findByLogin(login);

        if (user.isEmpty()) {
            throw new UserNotFound(login, true);
        }

        return true;
    }

    public String gerarTokenJwt(Authentication authentication, String ipClient) {
        UserSystem acesso = (UserSystem) authentication.getPrincipal();
        Date hoje = new Date();

        Date dataExpiracao = new Date(hoje.getTime() + ConstHelper.EXPIRATION_TIME);
        Map<String, Object> mapCorpo = new HashMap<String, Object>();
        mapCorpo.put("cer", DigestUtils.sha512Hex(ipClient));

        return Jwts.builder()
                .setIssuer("API ABC TECH")
                .setSubject(acesso.getUsuario().getId().toString())
                .setIssuedAt(hoje)
                .addClaims(mapCorpo)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS512, ConstHelper.KEY)
                .compact();
    }


    public boolean isTokenValido(String token, String ipRequisicao, String serverIp) {
        try {
            Jwts.parser().setSigningKey(ConstHelper.KEY).parseClaimsJws(token);

            Claims corpo = this.getCorpoToken(token);

            String ipToken = corpo.get("cer", String.class);

            boolean tokenValido = ipToken.equals(DigestUtils.sha512Hex(ipRequisicao));

            return tokenValido;

        } catch (Exception ex) {
            return false;
        }

    }
}
