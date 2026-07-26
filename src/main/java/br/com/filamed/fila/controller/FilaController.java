package br.com.filamed.fila.controller;

import br.com.filamed.exception.ApiErrorResponse;
import br.com.filamed.fila.dto.request.CadastroFilaRequest;
import br.com.filamed.fila.dto.response.FilaResponse;
import br.com.filamed.fila.service.FilaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fila")
public class FilaController {

    private final FilaService filaService;

    public FilaController(FilaService filaService) {
        this.filaService = filaService;
    }

    @Operation(summary = "Adicionar paciente à fila")
    @ApiResponse(responseCode = "201", description = "Paciente adicionado à fila")
    @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilaResponse cadastrar(
            @Valid @RequestBody CadastroFilaRequest request
    ) {
        return filaService.cadastrar(request);
    }

    @Operation(summary = "Listar toda a fila")
    @GetMapping
    public List<FilaResponse> listar() {
        return filaService.listar();
    }

    @Operation(summary = "Listar pacientes aguardando")
    @GetMapping("/aguardando")
    public List<FilaResponse> listarAguardando() {
        return filaService.listarAguardando();
    }

    @Operation(summary = "Buscar registro da fila por ID")
    @GetMapping("/{id}")
    public FilaResponse buscarPorId(@PathVariable Long id) {
        return filaService.buscarPorId(id);
    }

    @Operation(summary = "Consultar situação do paciente na fila")
    @GetMapping("/paciente/{pacienteId}")
    public FilaResponse buscarPorPaciente(
            @PathVariable Long pacienteId
    ) {
        return filaService.buscarPorPaciente(pacienteId);
    }

    @Operation(summary = "Chamar próximo paciente")
    @PostMapping("/proximo")
    public FilaResponse chamarProximo() {
        return filaService.chamarProximo();
    }

    @Operation(summary = "Finalizar atendimento")
    @PutMapping("/{id}/finalizar")
    public FilaResponse finalizarAtendimento(
            @PathVariable Long id
    ) {
        return filaService.finalizarAtendimento(id);
    }

}