package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.handler.exception.UserNotFound;
import br.com.fiap.abctechservice.model.Usuario;
import br.com.fiap.abctechservice.repository.UserRepository;
import br.com.fiap.abctechservice.security.UserSystem;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public ApplicationUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> user = repository.findByLogin(username);

        if (user.isEmpty()) {
            throw new UserNotFound(username, true);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        return new UserSystem(user.get(), authorities);
    }
}
