package com.agenda.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.agenda.api.domain.agenda.Agenda;
import com.agenda.api.domain.agenda.AgendaRepository;
import com.agenda.api.domain.agenda.DadosAtualizarAgenda;
import com.agenda.api.domain.agenda.DadosCadastroAgenda;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    public Agenda saveAgenda(DadosCadastroAgenda dados) {
        var agenda = new Agenda(dados);
        return repository.save(agenda);
    }

    public Page<Agenda> findAllActive(org.springframework.data.domain.Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    public Optional<Agenda> findById(Long id) {
        return repository.findById(id);
    }

    public Agenda updateAgenda(DadosAtualizarAgenda dados) {
        var agenda = repository.getOne(dados.id());
        agenda.atualizarDados(dados);
        return repository.save(agenda);
    }

    public void deleteAgenda(Long id) {
        var agenda = repository.getOne(id);
        agenda.excluir();
        repository.save(agenda);
    }
}
