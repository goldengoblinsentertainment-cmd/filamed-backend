package br.com.filamed.fila.repository;

import br.com.filamed.fila.entity.FilaAtendimento;
import br.com.filamed.fila.entity.StatusFila;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilaAtendimentoRepository
        extends JpaRepository<FilaAtendimento, Long> {

    List<FilaAtendimento> findAllByOrderByEntradaFilaAsc();

    List<FilaAtendimento> findByStatusOrderByEntradaFilaAsc(
            StatusFila status
    );

    Optional<FilaAtendimento> findFirstByStatusOrderByEntradaFilaAsc(
            StatusFila status
    );

    boolean existsByPacienteIdAndStatus(
            Long pacienteId,
            StatusFila status
    );
}