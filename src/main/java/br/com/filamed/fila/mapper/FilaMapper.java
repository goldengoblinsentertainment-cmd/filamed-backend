package br.com.filamed.fila.mapper;

import br.com.filamed.fila.dto.response.FilaResponse;
import br.com.filamed.fila.entity.FilaAtendimento;
import org.springframework.stereotype.Component;

@Component
public class FilaMapper {

    public FilaResponse toResponse(
            FilaAtendimento fila,
            Integer posicao,
            Integer pacientesNaFrente,
            Integer tempoEstimadoMinutos
    ) {

        return new FilaResponse(
                fila.getId(),
                fila.getSenha(),
                fila.getPaciente().getNomeCompleto(),
                fila.getEntradaFila(),
                fila.getStatus(),
                posicao,
                pacientesNaFrente,
                tempoEstimadoMinutos
        );

    }

}