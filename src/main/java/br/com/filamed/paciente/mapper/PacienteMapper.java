package br.com.filamed.paciente.mapper;

import br.com.filamed.paciente.dto.response.PacienteResponse;
import br.com.filamed.paciente.entity.Paciente;

public final class PacienteMapper {

    private PacienteMapper() {
    }

    public static PacienteResponse paraResponse(Paciente paciente) {
        if (paciente == null) {
            return null;
        }

        return new PacienteResponse(
                paciente.getId(),
                paciente.getNomeCompleto(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getTelefone(),
                paciente.getEmail(),
                paciente.getFotoUrl(),
                paciente.getCadastradoEm()
        );
    }
}
