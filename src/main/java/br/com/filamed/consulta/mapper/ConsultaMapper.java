package br.com.filamed.consulta.mapper;

import br.com.filamed.consulta.dto.response.ConsultaResponse;
import br.com.filamed.consulta.entity.Consulta;

public final class ConsultaMapper {

    private ConsultaMapper() {
    }

    public static ConsultaResponse paraResponse(Consulta consulta) {

        if (consulta == null) {
            return null;
        }

        return new ConsultaResponse(
                consulta.getId(),
                consulta.getPaciente().getNomeCompleto(),
                consulta.getMedico().getNome(),
                consulta.getMedico().getEspecialidade().getNome(),
                consulta.getDataHora(),
                consulta.getObservacao()
        );
    }
}