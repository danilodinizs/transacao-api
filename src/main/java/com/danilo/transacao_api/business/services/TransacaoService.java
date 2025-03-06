package com.danilo.transacao_api.business.services;

import com.danilo.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto) {
        if(dto.dataHora().isAfter(OffsetDateTime.now())) {

        }
    }

}
