package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.handler.exception.UserNotFound;
import br.com.fiap.abctechservice.model.Usuario;
import br.com.fiap.abctechservice.repository.UserRepository;
import br.com.fiap.abctechservice.security.UserSystem;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepository applicationUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> user = this.applicationUserRepository.findByLogin(username);

        if (!user.isPresent()) {
            throw new UserNotFound(username, true);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        UserSystem  userSystem = new UserSystem(user.get(), authorities);

        return userSystem;
    }
}
