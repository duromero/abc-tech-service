package br.com.fiap.abctechservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {

    @NotNull
    @Positive
    private Long operatorId;

    @NotEmpty
    @NotNull
    private List<Long> services;
    private OrderLocationDto start;
    private OrderLocationDto end;

}
