package br.com.fiap.abctechservice.util;

import br.com.fiap.abctechservice.model.Usuario;

public class Users {
    public static final String EXPIRED_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJBUEkgQUJDIFRFQ0giLCJzdWIiOiIxIiwiaWF0IjoxNjUxMDEyMDY3LCJjZXIiOiIxNGZkNDJjZjhjOWVjNjEyNjcyZWNjNmE3Y2Y3YzRkMjY2YzU3NTRiNWYzY2I4NmRlYjk0MjE5NjliZjZmYTYyNjJmNzU3ZGQ2MzYwYTdlYWY1NTk1MmMwNmNlMThlMmJjNzcyNzk1ZTlkMjBjZjVlNzUzNDQyYzRiZjUyNmZjNCIsImV4cCI6MTY1MTAxMjY2N30.Iba3YQIfIAA2wTqizJeQPW7O6pFxzaf2LADy8b4pkiFTbibhD5iKR5n0KSvnvWkV4744VK7nPTjijv0PIFEREA";

    public static Usuario getValidUser() {
        final var usuario = new Usuario();
        usuario.setName("Joao da Silva");
        usuario.setLogin("username");
        usuario.setPassword("123456");
        usuario.setActive(true);
        return usuario;
    }
}
