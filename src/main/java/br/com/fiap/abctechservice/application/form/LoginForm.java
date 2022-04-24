package br.com.fiap.abctechservice.application.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class LoginForm {

    @NotNull(message = "usuário deve ser preenchido")
    @Size(max = 120, min = 5, message = "Campo deve conter no mínimo 5 e no máximo 60 caracteres")
    private String usuario;

    @NotNull(message = "senha deve ser preenchido")
    @Size(max = 120, min = 5, message = "Campo deve conter no mínimo 5 e no máximo 60 caracteres")
    private String senha;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(usuario, senha);
    }
}
