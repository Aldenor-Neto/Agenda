package com.agenda.api.domain.agenda;

public record DadosListagemAgenda(Long id, String nome) {

    public DadosListagemAgenda(Agenda agenda) {
        this(agenda.getId(), agenda.getNome());
    }
}
