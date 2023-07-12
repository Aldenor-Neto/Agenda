package com.agenda.api.domain.agenda;

import com.agenda.api.domain.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarAgenda(@NotNull Long id, String nome, String email, String telefone,
        DadosEndereco endereco) {

}
