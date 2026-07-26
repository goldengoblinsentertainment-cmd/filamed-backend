package br.com.filamed.paciente.controller;

import br.com.filamed.paciente.dto.AtualizacaoPacienteRequest;
import br.com.filamed.paciente.dto.CadastroPacienteRequest;
import br.com.filamed.paciente.dto.response.PacienteResponse;
import br.com.filamed.paciente.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponse cadastrar(
            @Valid @RequestBody CadastroPacienteRequest request
    ) {
        return pacienteService.cadastrar(request);
    }

    @GetMapping
    public List<PacienteResponse> listarTodos() {
        return pacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public PacienteResponse buscarPorId(
            @PathVariable Long id
    ) {
        return pacienteService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public PacienteResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizacaoPacienteRequest request
    ) {
        return pacienteService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @PathVariable Long id
    ) {
        pacienteService.excluir(id);
    }
}