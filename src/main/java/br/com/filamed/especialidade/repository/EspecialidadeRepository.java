package br.com.filamed.especialidade.repository;

import br.com.filamed.especialidade.entity.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadeRepository
        extends JpaRepository<Especialidade, Long> {

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCaseAndIdNot(
            String nome,
            Long id
    );
}