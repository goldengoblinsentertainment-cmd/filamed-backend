package br.com.filamed.paciente.service;

import br.com.filamed.paciente.dto.AtualizacaoPacienteRequest;
import br.com.filamed.paciente.dto.CadastroPacienteRequest;
import br.com.filamed.paciente.model.Paciente;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PacienteService {

    private final Map<Long, Paciente> pacientes =
            new ConcurrentHashMap<>();

    private final AtomicLong proximoId =
            new AtomicLong(1);

    public Paciente cadastrar(
            CadastroPacienteRequest request
    ) {
        validarCpfDuplicadoNoCadastro(request.cpf());

        Long id = proximoId.getAndIncrement();

        Paciente paciente = new Paciente(
                id,
                request.nomeCompleto(),
                request.cpf(),
                request.dataNascimento(),
                request.telefone(),
                request.email(),
                request.fotoUrl(),
                LocalDateTime.now()
        );

        pacientes.put(id, paciente);

        return paciente;
    }

    public List<Paciente> listarTodos() {
        return pacientes.values()
                .stream()
                .sorted(Comparator.comparing(Paciente::getId))
                .toList();
    }

    public Paciente buscarPorId(Long id) {
        Paciente paciente = pacientes.get(id);

        if (paciente == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Paciente não encontrado"
            );
        }

        return paciente;
    }

    public Paciente atualizar(
            Long id,
            AtualizacaoPacienteRequest request
    ) {
        Paciente paciente = buscarPorId(id);

        validarCpfDuplicadoNaAtualizacao(
                id,
                request.cpf()
        );

        paciente.setNomeCompleto(request.nomeCompleto());
        paciente.setCpf(request.cpf());
        paciente.setDataNascimento(request.dataNascimento());
        paciente.setTelefone(request.telefone());
        paciente.setEmail(request.email());
        paciente.setFotoUrl(request.fotoUrl());

        pacientes.put(id, paciente);

        return paciente;
    }

    public void excluir(Long id) {
        Paciente paciente = buscarPorId(id);

        pacientes.remove(paciente.getId());
    }

    private void validarCpfDuplicadoNoCadastro(
            String cpf
    ) {
        boolean cpfJaCadastrado = pacientes.values()
                .stream()
                .anyMatch(paciente ->
                        paciente.getCpf().equals(cpf)
                );

        if (cpfJaCadastrado) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe um paciente cadastrado com este CPF"
            );
        }
    }

    private void validarCpfDuplicadoNaAtualizacao(
            Long id,
            String cpf
    ) {
        boolean cpfPertenceAOutroPaciente =
                pacientes.values()
                        .stream()
                        .filter(paciente ->
                                !paciente.getId().equals(id)
                        )
                        .anyMatch(paciente ->
                                paciente.getCpf().equals(cpf)
                        );

        if (cpfPertenceAOutroPaciente) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe outro paciente cadastrado com este CPF"
            );
        }
    }
}