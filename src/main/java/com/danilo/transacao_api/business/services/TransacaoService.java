package com.danilo.transacao_api.business.services;

import com.danilo.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.danilo.transacao_api.infrasctructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);
    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto) {

        log.info("Iniciado o processamento de gravar transações " + dto);
        if(dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maiores que a data e hora atuais");
            throw new UnprocessableEntity("");
        }
        if(dto.valor() < 0) {
            log.error("Valor não pode ser menor que 0");
            throw new UnprocessableEntity("");
        }
        log.info("Transações adicionadas com sucesso.");
        listaTransacoes.add(dto);
    }

    public void limparTransacoes() {
        log.info("Iniciado processamento para deletar transações");
        listaTransacoes.clear();
        log.info("Transações deletadas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca) {
        log.info("Iniciada a busca de transações por tempo");
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);
        log.info("Retorno de transações com sucesso.");
        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora()
                        .isAfter(dataHoraIntervalo)).toList();
    }

}
