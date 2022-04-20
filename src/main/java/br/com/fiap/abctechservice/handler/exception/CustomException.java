package br.com.fiap.abctechservice.handler.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final int status;
    private final String description;

    public CustomException(int status, String message, String description) {
        super(message);
        this.status = status;
        this.description=description;
    }
}