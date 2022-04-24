package br.com.fiap.abctechservice.security;

import br.com.fiap.abctechservice.model.Usuario;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class UserSystem extends User {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    public UserSystem(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getLogin(),
                usuario.getPassword(),
                authorities);
        this.usuario = usuario;
    }

}
