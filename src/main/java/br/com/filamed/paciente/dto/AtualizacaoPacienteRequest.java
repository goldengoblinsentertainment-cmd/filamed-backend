package br.com.filamed.paciente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AtualizacaoPacienteRequest(

        @NotBlank(message = "O nome completo é obrigatório")
        @Size(
                min = 3,
                max = 150,
                message = "O nome deve ter entre 3 e 150 caracteres"
        )
        String nomeCompleto,

        @NotBlank(message = "O CPF é obrigatório")
        @Size(
                min = 11,
                max = 14,
                message = "O CPF deve ter entre 11 e 14 caracteres"
        )
        String cpf,

        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate dataNascimento,

        @NotBlank(message = "O telefone é obrigatório")
        String telefone,

        @Email(message = "Informe um e-mail válido")
        String email,

        String fotoUrl
) {
}
