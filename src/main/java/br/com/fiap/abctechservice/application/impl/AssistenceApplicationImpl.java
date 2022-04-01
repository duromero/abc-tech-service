package br.com.fiap.abctechservice.application.impl;

import br.com.fiap.abctechservice.application.AssistenceApplication;
import br.com.fiap.abctechservice.application.dto.AssistDto;
import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.service.impl.AssistanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssistenceApplicationImpl implements AssistenceApplication {

    private final AssistanceServiceImpl service;

    public AssistenceApplicationImpl(@Autowired AssistanceServiceImpl service) {
        this.service = service;
    }

    @Override
    public List<AssistDto> getAssists() {
        List<Assistance> list = this.service.getAssistList();
        return list.stream().map(assitance -> new AssistDto(assitance.getId(), assitance.getName(), assitance.getDescription())).collect(Collectors.toList());
    }
}
