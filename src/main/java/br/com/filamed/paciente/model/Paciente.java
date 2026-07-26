package br.com.filamed.paciente.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Paciente {

    private Long id;
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private String fotoUrl;
    private LocalDateTime cadastradoEm;

    public Paciente() {
    }

    public Paciente(
            Long id,
            String nomeCompleto,
            String cpf,
            LocalDate dataNascimento,
            String telefone,
            String email,
            String fotoUrl,
            LocalDateTime cadastradoEm
    ) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.fotoUrl = fotoUrl;
        this.cadastradoEm = cadastradoEm;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public LocalDateTime getCadastradoEm() {
        return cadastradoEm;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public void setCadastradoEm(LocalDateTime cadastradoEm) {
        this.cadastradoEm = cadastradoEm;
    }
}