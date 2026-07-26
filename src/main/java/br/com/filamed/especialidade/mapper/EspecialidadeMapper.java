package br.com.filamed.especialidade.mapper;

import br.com.filamed.especialidade.dto.response.EspecialidadeResponse;
import br.com.filamed.especialidade.entity.Especialidade;

public final class EspecialidadeMapper {

    private EspecialidadeMapper() {
    }

    public static EspecialidadeResponse paraResponse(Especialidade especialidade) {

        if (especialidade == null) {
            return null;
        }

        return new EspecialidadeResponse(
                especialidade.getId(),
                especialidade.getNome(),
                especialidade.getDescricao(),
                especialidade.getAtivo(),
                especialidade.getCriadoEm()
        );
    }
}