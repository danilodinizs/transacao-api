package com.danilo.transacao_api.controller;

import com.danilo.transacao_api.business.services.TransacaoService;
import com.danilo.transacao_api.controller.dtos.TransacaoRequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    @Operation(description = "Endpoint responsável por adicionar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação gravada com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação!"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição!"),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor!")
    })
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto) {
        transacaoService.adicionarTransacoes(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsável por deletar transações!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação deletada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição!"),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor!")
    })
    public ResponseEntity<Void> deletarTransacoes() {
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }
}
