package br.com.fiap.abctechservice.handler.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;

    private String userMessage;

    private String timestamp;

    private List<Erros> objects;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Erros {

        private String name;
        private String userMessage;

    }

}