package com.agenda.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.agenda.api.domain.agenda.Agenda;
import com.agenda.api.domain.agenda.AgendaRepository;
import com.agenda.api.domain.agenda.DadosAtualizarAgenda;
import com.agenda.api.domain.agenda.DadosCadastroAgenda;
import com.agenda.api.domain.agenda.DadosDetalhamentoAgenda;
import com.agenda.api.domain.agenda.DadosListagemAgenda;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/agenda")

public class AgendaController {

    @Autowired
    private AgendaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroAgenda dados, UriComponentsBuilder uriBuilder) {

        var agenda = new Agenda(dados);
        repository.save(agenda);

        var uri = uriBuilder.path("/agenda/{id}").buildAndExpand(agenda.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAgenda(agenda));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAgenda>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {

        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemAgenda::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {

        var agenda = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoAgenda(agenda));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizarAgenda dados) {

        var agenda = repository.getReferenceById(dados.id());
        agenda.atualizarDados(dados);

        return ResponseEntity.ok(new DadosDetalhamentoAgenda(agenda));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {

        var agenda = repository.getReferenceById(id);
        agenda.excluir(agenda);

        return ResponseEntity.noContent().build();
    }

}
