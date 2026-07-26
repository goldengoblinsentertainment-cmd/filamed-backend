package br.com.filamed.especialidade.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroEspecialidadeRequest(

        @Schema(
                description = "Nome da especialidade",
                example = "Cardiologia"
        )
        @NotBlank(message = "O nome da especialidade é obrigatório")
        @Size(max = 100, message = "O nome deve possuir no máximo 100 caracteres")
        String nome,

        @Schema(
                description = "Descrição da especialidade",
                example = "Especialidade responsável pelo diagnóstico e tratamento de doenças do coração."
        )
        @Size(max = 500, message = "A descrição deve possuir no máximo 500 caracteres")
        String descricao

) {
}