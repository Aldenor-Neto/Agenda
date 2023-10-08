package com.agenda.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agenda.api.domain.agenda.Agenda;
import com.agenda.api.domain.agenda.AgendaRepository;
import com.agenda.api.domain.agenda.DadosCadastroAgenda;
import com.agenda.api.domain.endereco.DadosEndereco;
import com.agenda.api.service.AgendaService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AgendaServiceTest {
    
    @Mock
    private AgendaRepository repository; // Mock do repositório

    @InjectMocks
    private AgendaService service; // Serviço que será testado, com o repositório mockado injetado

    @Test
    void shouldSaveAgenda() {
        // Preparação do teste
        DadosEndereco endereco = new DadosEndereco(
                "Rua Principal",
                "28",
                "próximo a uma loja",
                "Piaçaveira",
                "Camaçari",
                "BA");

        DadosCadastroAgenda dados = new DadosCadastroAgenda("João", "joao@email.com", "123456789", endereco);

        Agenda expectedAgenda = new Agenda(dados);

        // Configuração do mock: quando o método save() do repositório for chamado,
        // retorna a agenda esperada
        when(repository.save(any(Agenda.class))).thenReturn(expectedAgenda);

        // Ação: chama o método do serviço que queremos testar
        Agenda result = service.saveAgenda(dados);

        // Verificação: compara o resultado com o esperado
        assertEquals(expectedAgenda, result);
    }
}