package br.com.filamed.consulta.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ConsultaResponse(

        @Schema(
                description = "Identificador da consulta",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nome do paciente",
                example = "Maria Silva"
        )
        String paciente,

        @Schema(
                description = "Nome do médico",
                example = "Dr. João da Silva"
        )
        String medico,

        @Schema(
                description = "Especialidade do médico",
                example = "Cardiologia"
        )
        String especialidade,

        @Schema(
                description = "Data e hora da consulta",
                example = "2026-08-10T14:30:00"
        )
        LocalDateTime dataHora,

        @Schema(
                description = "Observação da consulta",
                example = "Paciente com retorno em 30 dias."
        )
        String observacao

) {
}