package com.danilo.transacao_api.business.services;

import com.danilo.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.danilo.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticaseTransacoes(Integer intervaloDeBusca) {
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloDeBusca);

        DoubleSummaryStatistics
    }
}
