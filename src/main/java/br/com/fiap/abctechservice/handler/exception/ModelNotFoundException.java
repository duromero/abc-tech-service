package br.com.fiap.abctechservice.handler.exception;

public abstract class ModelNotFoundException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public ModelNotFoundException(String mensagem) {
        super(mensagem);
    }

}