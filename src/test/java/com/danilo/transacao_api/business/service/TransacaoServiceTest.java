package com.danilo.transacao_api.business.service;

import com.danilo.transacao_api.business.services.EstatisticasService;
import com.danilo.transacao_api.business.services.TransacaoService;
import com.danilo.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.danilo.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.danilo.transacao_api.infrasctructure.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;

    @BeforeEach
    void setUp() {
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        // estatisticas = new EstatisticasResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void adicionarTransacaoComSucesso() {
        transacaoService.adicionarTransacoes(transacao);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        Assertions.assertTrue(transacoes.contains(transacao));
    }

    @Test
    void deveLancarExcecaoCasoValorSejaNegativo() {
        UnprocessableEntity exception = Assertions.assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacoes(new TransacaoRequestDTO(-10.0, OffsetDateTime.now())));

        Assertions.assertEquals("Valor não pode ser menor que 0", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoCasoDataOuHoraSejaFutura() {
        UnprocessableEntity exception = Assertions.assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacoes(new TransacaoRequestDTO(10.0, OffsetDateTime.now().plusDays(1))));

        Assertions.assertEquals("Data e hora maiores que a data e hora atuais", exception.getMessage());
    }

    @Test
    void limparTransacoesComSucesso() {
        transacaoService.limparTransacoes();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        Assertions.assertTrue(transacoes.isEmpty());
    }

    @Test
    void buscarTransacoesDentroDoIntervaloComSucesso() {

        TransacaoRequestDTO dto = new TransacaoRequestDTO(20.0, OffsetDateTime.now().minusSeconds(61L));

        transacaoService.adicionarTransacoes(transacao);
        transacaoService.adicionarTransacoes(dto);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(60);

        Assertions.assertTrue(transacoes.contains(transacao));
        Assertions.assertFalse(transacoes.contains(dto));
    }
}
