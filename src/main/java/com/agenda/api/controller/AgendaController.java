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

import com.agenda.api.domain.agenda.DadosAtualizarAgenda;
import com.agenda.api.domain.agenda.DadosCadastroAgenda;
import com.agenda.api.domain.agenda.DadosDetalhamentoAgenda;
import com.agenda.api.domain.agenda.DadosListagemAgenda;
import com.agenda.api.service.AgendaService;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoAgenda> cadastrar(@RequestBody DadosCadastroAgenda dados,
            UriComponentsBuilder uriBuilder) {
        var agenda = service.saveAgenda(dados);
        var uri = uriBuilder.path("/agenda/{id}").buildAndExpand(agenda.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAgenda(agenda));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAgenda>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        var page = service.findAllActive(paginacao).map(DadosListagemAgenda::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoAgenda> detalhar(@PathVariable Long id) {
        return service.findById(id)
                .map(agenda -> ResponseEntity.ok(new DadosDetalhamentoAgenda(agenda)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoAgenda> atualizar(@RequestBody DadosAtualizarAgenda dados) {
        var agenda = service.updateAgenda(dados);
        return ResponseEntity.ok(new DadosDetalhamentoAgenda(agenda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.deleteAgenda(id);
        return ResponseEntity.noContent().build();
    }
}
