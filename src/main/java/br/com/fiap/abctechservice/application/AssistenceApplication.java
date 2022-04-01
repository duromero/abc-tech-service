package br.com.fiap.abctechservice.application;

import br.com.fiap.abctechservice.application.dto.AssistDto;
import br.com.fiap.abctechservice.service.impl.AssistanceServiceImpl;

import java.util.List;

public interface AssistenceApplication {



    public List<AssistDto> getAssists();
}
