package br.com.filamed.fila.dto.response;

import br.com.filamed.fila.entity.StatusFila;

import java.time.LocalDateTime;

public record FilaResponse(

        Long id,
        Integer senha,
        String paciente,
        LocalDateTime entradaFila,
        StatusFila status,
        Integer posicao,
        Integer pacientesNaFrente,
        Integer tempoEstimadoMinutos

) {
}