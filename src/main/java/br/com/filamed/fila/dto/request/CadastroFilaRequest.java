package br.com.filamed.fila.dto.request;

import jakarta.validation.constraints.NotNull;

public record CadastroFilaRequest(

        @NotNull(message = "O ID do paciente é obrigatório.")
        Long pacienteId

) {
}