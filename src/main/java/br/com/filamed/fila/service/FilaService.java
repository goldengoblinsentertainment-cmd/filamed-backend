package br.com.filamed.fila.service;

import br.com.filamed.fila.dto.request.CadastroFilaRequest;
import br.com.filamed.fila.dto.response.FilaResponse;
import br.com.filamed.fila.entity.FilaAtendimento;
import br.com.filamed.fila.entity.StatusFila;
import br.com.filamed.fila.mapper.FilaMapper;
import br.com.filamed.fila.repository.FilaAtendimentoRepository;
import br.com.filamed.paciente.entity.Paciente;
import br.com.filamed.paciente.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FilaService {

    private final FilaAtendimentoRepository filaRepository;
    private final PacienteRepository pacienteRepository;
    private final FilaMapper filaMapper;

    public FilaService(
            FilaAtendimentoRepository filaRepository,
            PacienteRepository pacienteRepository,
            FilaMapper filaMapper
    ) {
        this.filaRepository = filaRepository;
        this.pacienteRepository = pacienteRepository;
        this.filaMapper = filaMapper;
    }

    @Transactional
    public FilaResponse cadastrar(CadastroFilaRequest request) {

        Paciente paciente = pacienteRepository.findById(request.pacienteId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Paciente não encontrado.")
                );

        boolean aguardando = filaRepository.existsByPacienteIdAndStatus(
                paciente.getId(),
                StatusFila.AGUARDANDO
        );

        boolean emAtendimento = filaRepository.existsByPacienteIdAndStatus(
                paciente.getId(),
                StatusFila.EM_ATENDIMENTO
        );

        if (aguardando || emAtendimento) {
            throw new IllegalStateException(
                    "O paciente já está na fila de atendimento."
            );
        }

        int novaSenha = (int) filaRepository.count() + 1;

        FilaAtendimento fila = new FilaAtendimento(
                paciente,
                novaSenha,
                LocalDateTime.now(),
                StatusFila.AGUARDANDO
        );

        filaRepository.save(fila);

        return montarResposta(fila);
    }

    public List<FilaResponse> listar() {

        return filaRepository.findAllByOrderByEntradaFilaAsc()
                .stream()
                .map(this::montarResposta)
                .toList();
    }

    public List<FilaResponse> listarAguardando() {

        return filaRepository
                .findByStatusOrderByEntradaFilaAsc(StatusFila.AGUARDANDO)
                .stream()
                .map(this::montarResposta)
                .toList();
    }

    public FilaResponse buscarPorId(Long id) {

        FilaAtendimento fila = buscarEntidadePorId(id);

        return montarResposta(fila);
    }

    public FilaResponse buscarPorPaciente(Long pacienteId) {

        FilaAtendimento fila = filaRepository.findAllByOrderByEntradaFilaAsc()
                .stream()
                .filter(f -> f.getPaciente().getId().equals(pacienteId))
                .filter(f -> f.getStatus() != StatusFila.ATENDIDO)
                .findFirst()
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Paciente não está na fila."
                        )
                );

        return montarResposta(fila);
    }

    @Transactional
    public FilaResponse chamarProximo() {

        FilaAtendimento proximo = filaRepository
                .findFirstByStatusOrderByEntradaFilaAsc(StatusFila.AGUARDANDO)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Não existem pacientes aguardando na fila."
                        )
                );

        proximo.setStatus(StatusFila.EM_ATENDIMENTO);

        filaRepository.save(proximo);

        return montarResposta(proximo);
    }

    @Transactional
    public FilaResponse finalizarAtendimento(Long id) {

        FilaAtendimento fila = buscarEntidadePorId(id);

        if (fila.getStatus() != StatusFila.EM_ATENDIMENTO) {
            throw new IllegalStateException(
                    "Somente um atendimento em andamento pode ser finalizado."
            );
        }

        fila.setStatus(StatusFila.ATENDIDO);

        filaRepository.save(fila);

        return montarResposta(fila);
    }

    private FilaResponse montarResposta(FilaAtendimento fila) {

        int posicao = 0;

        if (fila.getStatus() == StatusFila.AGUARDANDO) {

            List<FilaAtendimento> aguardando = filaRepository
                    .findByStatusOrderByEntradaFilaAsc(
                            StatusFila.AGUARDANDO
                    );

            for (int i = 0; i < aguardando.size(); i++) {

                if (aguardando.get(i).getId().equals(fila.getId())) {
                    posicao = i + 1;
                    break;
                }
            }
        }

        int pacientesNaFrente = posicao > 0
                ? posicao - 1
                : 0;

        int tempoEstimadoMinutos = pacientesNaFrente * 15;

        return filaMapper.toResponse(
                fila,
                posicao,
                pacientesNaFrente,
                tempoEstimadoMinutos
        );
    }

    private FilaAtendimento buscarEntidadePorId(Long id) {

        return filaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Registro da fila não encontrado."
                        )
                );
    }
}