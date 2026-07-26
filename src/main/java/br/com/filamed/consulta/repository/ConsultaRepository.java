package br.com.filamed.consulta.repository;

import br.com.filamed.consulta.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}