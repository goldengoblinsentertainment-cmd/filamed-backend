package br.com.filamed.especialidade.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record EspecialidadeResponse(

        @Schema(
                description = "Identificador único da especialidade",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nome da especialidade",
                example = "Cardiologia"
        )
        String nome,

        @Schema(
                description = "Descrição da especialidade",
                example = "Especialidade responsável pelo diagnóstico e tratamento de doenças do coração."
        )
        String descricao,

        @Schema(
                description = "Indica se a especialidade está ativa",
                example = "true"
        )
        Boolean ativo,

        @Schema(
                description = "Data e hora de criação do cadastro",
                example = "2026-07-26T16:30:00"
        )
        LocalDateTime criadoEm

) {
}