package com.agenda.api.domain.agenda;

import com.agenda.api.domain.endereco.Endereco;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "agenda")
@Entity(name = "Agenda")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;

    @Embedded
    Endereco endereco;

    private boolean ativo;

    public Agenda(DadosCadastroAgenda dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarDados(DadosAtualizarAgenda dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.email() != null) {
            this.email = dados.email();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if (dados.endereco() != null) {
            endereco.atualizarDados(dados.endereco());
        }
    }

    public void excluir(Agenda agenda) {
        this.ativo = false;
    }

}
