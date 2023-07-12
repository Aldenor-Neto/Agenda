package com.agenda.api.domain.agenda;

import com.agenda.api.domain.endereco.Endereco;

public record DadosDetalhamentoAgenda(Long id, String nome, String email, String telefone, Endereco endereco) {

    public DadosDetalhamentoAgenda(Agenda agenda) {
        this(
                agenda.getId(),
                agenda.getNome(),
                agenda.getEmail(),
                agenda.getTelefone(),
                agenda.getEndereco());
    }
}
