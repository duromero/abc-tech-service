package br.com.fiap.abctechservice.util;

import br.com.fiap.abctechservice.model.Usuario;

public class Users {
    public static Usuario getValidUser() {
        final var usuario = new Usuario();
        usuario.setName("Joao da Silva");
        usuario.setLogin("username");
        usuario.setPassword("123456");
        usuario.setActive(true);
        return usuario;
    }
}
