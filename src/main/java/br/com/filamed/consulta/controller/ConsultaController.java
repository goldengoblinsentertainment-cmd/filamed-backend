package br.com.filamed.consulta.controller;

import br.com.filamed.consulta.dto.request.CadastroConsultaRequest;
import br.com.filamed.consulta.dto.response.ConsultaResponse;
import br.com.filamed.consulta.service.ConsultaService;
import br.com.filamed.exception.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@Tag(name = "Consultas", description = "Gerenciamento de consultas")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastrar consulta")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Consulta cadastrada com sucesso",
                    content = @Content(schema = @Schema(implementation = ConsultaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Paciente ou médico não encontrados",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ConsultaResponse> cadastrar(
            @Valid @RequestBody CadastroConsultaRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.cadastrar(request));
    }

    @GetMapping
    @Operation(summary = "Listar consultas")
    public ResponseEntity<List<ConsultaResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar consulta por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta encontrada"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ConsultaResponse> buscarPorId(

            @Parameter(description = "ID da consulta")
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar consulta")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta atualizada"),
            @ApiResponse(responseCode = "404", description = "Consulta, paciente ou médico não encontrados",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ConsultaResponse> atualizar(

            @PathVariable Long id,

            @Valid
            @RequestBody CadastroConsultaRequest request) {

        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir consulta")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Consulta excluída"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<Void> excluir(

            @Parameter(description = "ID da consulta")
            @PathVariable Long id) {

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}