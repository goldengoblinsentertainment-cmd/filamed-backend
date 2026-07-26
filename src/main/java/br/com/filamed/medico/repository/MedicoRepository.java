package br.com.filamed.medico.repository;

import br.com.filamed.medico.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    boolean existsByCrmIgnoreCase(String crm);

    boolean existsByCrmIgnoreCaseAndIdNot(
            String crm,
            Long id
    );
}