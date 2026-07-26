package br.com.filamed.paciente.service;

import br.com.filamed.paciente.dto.request.AtualizacaoPacienteRequest;
import br.com.filamed.paciente.dto.request.CadastroPacienteRequest;
import br.com.filamed.paciente.dto.response.PacienteResponse;
import br.com.filamed.paciente.entity.Paciente;
import br.com.filamed.paciente.mapper.PacienteMapper;
import br.com.filamed.paciente.repository.PacienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public PacienteResponse cadastrar(CadastroPacienteRequest request) {
        if (pacienteRepository.existsByCpf(request.cpf())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe um paciente cadastrado com este CPF"
            );
        }

        Paciente paciente = new Paciente(
                request.nomeCompleto(),
                request.cpf(),
                request.dataNascimento(),
                request.telefone(),
                request.email(),
                request.fotoUrl()
        );

        Paciente pacienteSalvo = pacienteRepository.save(paciente);

        return PacienteMapper.paraResponse(pacienteSalvo);
    }

    @Transactional(readOnly = true)
    public List<PacienteResponse> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteMapper::paraResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public PacienteResponse buscarPorId(Long id) {
        Paciente paciente = buscarEntidadePorId(id);

        return PacienteMapper.paraResponse(paciente);
    }

    @Transactional
    public PacienteResponse atualizar(
            Long id,
            AtualizacaoPacienteRequest request
    ) {
        Paciente paciente = buscarEntidadePorId(id);

        if (pacienteRepository.existsByCpfAndIdNot(request.cpf(), id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe outro paciente cadastrado com este CPF"
            );
        }

        paciente.setNomeCompleto(request.nomeCompleto());
        paciente.setCpf(request.cpf());
        paciente.setDataNascimento(request.dataNascimento());
        paciente.setTelefone(request.telefone());
        paciente.setEmail(request.email());
        paciente.setFotoUrl(request.fotoUrl());

        Paciente pacienteAtualizado = pacienteRepository.save(paciente);

        return PacienteMapper.paraResponse(pacienteAtualizado);
    }

    @Transactional
    public void excluir(Long id) {
        Paciente paciente = buscarEntidadePorId(id);

        pacienteRepository.delete(paciente);
    }

    private Paciente buscarEntidadePorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Paciente não encontrado"
                ));
    }
}