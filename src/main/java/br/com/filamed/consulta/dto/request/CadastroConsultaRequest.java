package br.com.filamed.consulta.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CadastroConsultaRequest(

        @Schema(
                description = "ID do paciente",
                example = "1"
        )
        @NotNull(message = "O paciente é obrigatório")
        Long pacienteId,

        @Schema(
                description = "ID do médico",
                example = "1"
        )
        @NotNull(message = "O médico é obrigatório")
        Long medicoId,

        @Schema(
                description = "Data e hora da consulta",
                example = "2026-08-10T14:30:00"
        )
        @NotNull(message = "A data da consulta é obrigatória")
        @Future(message = "A consulta deve ser agendada para uma data futura")
        LocalDateTime dataHora,

        @Schema(
                description = "Observações da consulta",
                example = "Paciente com retorno em 30 dias."
        )
        @Size(max = 500, message = "A observação deve possuir no máximo 500 caracteres")
        String observacao

) {
}