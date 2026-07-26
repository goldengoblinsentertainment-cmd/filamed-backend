package br.com.filamed.medico.mapper;

import br.com.filamed.medico.dto.response.MedicoResponse;
import br.com.filamed.medico.entity.Medico;

public final class MedicoMapper {

    private MedicoMapper() {
    }

    public static MedicoResponse paraResponse(Medico medico) {

        if (medico == null) {
            return null;
        }

        return new MedicoResponse(
                medico.getId(),
                medico.getNome(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEmail(),
                medico.getEspecialidade().getNome(),
                medico.getAtivo()
        );
    }
}