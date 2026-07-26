package br.com.filamed.paciente.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PacienteResponse(
        Long id,
        String nomeCompleto,
        String cpf,
        LocalDate dataNascimento,
        String telefone,
        String email,
        String fotoUrl,
        LocalDateTime cadastradoEm
) {
}
