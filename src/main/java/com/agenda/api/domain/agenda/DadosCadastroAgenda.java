package com.agenda.api.domain.agenda;

import com.agenda.api.domain.endereco.DadosEndereco;

import jakarta.validation.constraints.Email;

public record DadosCadastroAgenda(String nome, @Email String email, String telefone, DadosEndereco endereco) {

}
