package br.com.filamed.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiErrorResponse(

        LocalDateTime dataHora,

        Integer status,

        String erro,

        String mensagem,

        String caminho,

        Map<String, String> campos

) {

    public static ApiErrorResponse criar(
            Integer status,
            String erro,
            String mensagem,
            String caminho
    ) {
        return new ApiErrorResponse(
                LocalDateTime.now(),
                status,
                erro,
                mensagem,
                caminho,
                null
        );
    }

    public static ApiErrorResponse validacao(
            Integer status,
            String erro,
            String mensagem,
            String caminho,
            Map<String, String> campos
    ) {
        return new ApiErrorResponse(
                LocalDateTime.now(),
                status,
                erro,
                mensagem,
                caminho,
                campos
        );
    }
}