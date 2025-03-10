package com.danilo.transacao_api.controller;

import com.danilo.transacao_api.business.services.EstatisticasService;
import com.danilo.transacao_api.business.services.TransacaoService;
import com.danilo.transacao_api.controller.dtos.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    private EstatisticasService estatisticasService;

    public EstatisticasController(EstatisticasService estatisticasService) {
        this.estatisticasService = estatisticasService;
    }

    @GetMapping
    @Operation(description = "Endpoint responsável por buscar estatísticas de transações!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Busca efetuada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na busca!"),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor!")
    })
    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(@RequestParam(value = "intervaloDeBusca", required = false, defaultValue = "60") Integer intervaloDeBusca) {
        return ResponseEntity.ok(estatisticasService.calcularEstatisticaseTransacoes(intervaloDeBusca));
    }
}
