package com.danilo.transacao_api.business.services;

import com.danilo.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.danilo.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@Slf4j
public class EstatisticasService {

    private static final Logger log = LoggerFactory.getLogger(EstatisticasService.class);
    public TransacaoService transacaoService;

    public EstatisticasService(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    public EstatisticasResponseDTO calcularEstatisticaseTransacoes(Integer intervaloDeBusca) {

        log.info("Iniciada a busca de estatísticas de transações pelo período de tempo de " + intervaloDeBusca + " segundos");

        long start = System.currentTimeMillis();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloDeBusca);

        if (transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L,0.0,0.0,0.0,0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        long finish = System.currentTimeMillis();
        long tempo = finish - start;
        log.info("Tempo de requisição: " + tempo + "ms");

        log.info("Estatísticas retornadas com sucesso");

        return new EstatisticasResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
