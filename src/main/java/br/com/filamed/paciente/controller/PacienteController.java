package br.com.filamed.paciente.controller;

import br.com.filamed.paciente.dto.AtualizacaoPacienteRequest;
import br.com.filamed.paciente.dto.CadastroPacienteRequest;
import br.com.filamed.paciente.model.Paciente;
import br.com.filamed.paciente.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente cadastrar(
            @Valid @RequestBody CadastroPacienteRequest request
    ) {
        return pacienteService.cadastrar(request);
    }

    @GetMapping
    public List<Paciente> listarTodos() {
        return pacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Paciente buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Paciente atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizacaoPacienteRequest request
    ) {
        return pacienteService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        pacienteService.excluir(id);
    }
}