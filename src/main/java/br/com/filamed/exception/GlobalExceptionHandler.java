package br.com.filamed.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponse> tratarResponseStatusException(
            ResponseStatusException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.valueOf(
                exception.getStatusCode().value()
        );

        ApiErrorResponse resposta = new ApiErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                exception.getReason(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity
                .status(status)
                .body(resposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> tratarValidacao(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        Map<String, String> campos = new LinkedHashMap<>();

        for (FieldError erro : exception.getBindingResult().getFieldErrors()) {
            campos.put(
                    erro.getField(),
                    erro.getDefaultMessage()
            );
        }

        ApiErrorResponse resposta = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Existem campos inválidos na requisição",
                request.getRequestURI(),
                campos
        );

        return ResponseEntity
                .badRequest()
                .body(resposta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> tratarErroInterno(
            Exception exception,
            HttpServletRequest request
    ) {
        ApiErrorResponse resposta = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Ocorreu um erro interno no servidor",
                request.getRequestURI(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(resposta);
    }
}