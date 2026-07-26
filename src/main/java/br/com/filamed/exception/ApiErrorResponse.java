package br.com.filamed.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiErrorResponse(
        LocalDateTime timestamp,
        int status,
        String erro,
        String mensagem,
        String caminho,
        Map<String, String> campos
) {
}