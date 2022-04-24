package br.com.fiap.abctechservice.handler;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.abctechservice.handler.exception.Problem;
import br.com.fiap.abctechservice.handler.exception.ProblemType;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Component
public class RestAuthenticationFailureHandler implements AuthenticationEntryPoint {

    public @Override
    void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {

        HttpStatus status = HttpStatus.FORBIDDEN;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = "Acesso negado";
        List<Problem.Erros> objects = new ArrayList<>();
        Problem.Erros erro = new Problem.Erros("validação", "Acesso Negado");
        objects.add(erro);

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .objects(objects)
                .build();

        Gson json = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .serializeNulls()
                .create();

        response.setStatus(FORBIDDEN.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getWriter().write(json.toJson(problem));
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
                                                        ProblemType problemType, String detail) {

        return Problem.builder()
                .timestamp(OffsetDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

}

