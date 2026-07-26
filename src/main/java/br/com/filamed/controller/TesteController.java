package br.com.filamed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class TesteController {

    @GetMapping("/")
    public Map<String, Object> home() {

        return Map.of(
                "status", "ONLINE",
                "sistema", "FilaMed",
                "versao", "1.0",
                "dataHora", LocalDateTime.now()
        );
    }
}