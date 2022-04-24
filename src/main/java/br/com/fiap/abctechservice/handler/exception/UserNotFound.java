package br.com.fiap.abctechservice.handler.exception;

public class UserNotFound extends ModelNotFoundException {

    private static final long serialVersionUID = 1L;

    public UserNotFound(String mensagem) {
        super(mensagem);
    }

    public UserNotFound(String login, boolean loginInvalido) {
        this("Não existe um usuário cadastrado com o login: " + login);
    }
}