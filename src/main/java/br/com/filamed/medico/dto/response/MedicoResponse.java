package br.com.filamed.medico.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record MedicoResponse(

        @Schema(
                description = "Identificador do médico",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nome do médico",
                example = "João da Silva"
        )
        String nome,

        @Schema(
                description = "CRM do médico",
                example = "CRM-SP 123456"
        )
        String crm,

        @Schema(
                description = "Telefone do médico",
                example = "(19) 99999-9999"
        )
        String telefone,

        @Schema(
                description = "E-mail do médico",
                example = "joao@email.com"
        )
        String email,

        @Schema(
                description = "Especialidade do médico",
                example = "Cardiologia"
        )
        String especialidade,

        @Schema(
                description = "Indica se o médico está ativo",
                example = "true"
        )
        Boolean ativo

) {
}