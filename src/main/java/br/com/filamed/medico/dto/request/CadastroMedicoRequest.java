package br.com.filamed.medico.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastroMedicoRequest(

        @Schema(
                description = "Nome completo do médico",
                example = "João da Silva"
        )
        @NotBlank(message = "O nome do médico é obrigatório")
        @Size(max = 120, message = "O nome deve possuir no máximo 120 caracteres")
        String nome,

        @Schema(
                description = "CRM do médico",
                example = "CRM-SP 123456"
        )
        @NotBlank(message = "O CRM é obrigatório")
        @Size(max = 20, message = "O CRM deve possuir no máximo 20 caracteres")
        String crm,

        @Schema(
                description = "Telefone do médico",
                example = "(19) 99999-9999"
        )
        @Size(max = 20, message = "O telefone deve possuir no máximo 20 caracteres")
        String telefone,

        @Schema(
                description = "E-mail do médico",
                example = "joao.silva@email.com"
        )
        @Email(message = "O e-mail informado é inválido")
        @Size(max = 150, message = "O e-mail deve possuir no máximo 150 caracteres")
        String email,

        @Schema(
                description = "ID da especialidade do médico",
                example = "1"
        )
        @NotNull(message = "A especialidade é obrigatória")
        Long especialidadeId

) {
}