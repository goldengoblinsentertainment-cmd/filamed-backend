package br.com.filamed.paciente.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "pacientes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_paciente_cpf",
                        columnNames = "cpf"
                )
        }
)
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nomeCompleto;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(length = 150)
    private String email;

    private String fotoUrl;

    @Column(nullable = false, updatable = false)
    private LocalDateTime cadastradoEm;

    public Paciente() {
    }

    public Paciente(
            String nomeCompleto,
            String cpf,
            LocalDate dataNascimento,
            String telefone,
            String email,
            String fotoUrl
    ) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.fotoUrl = fotoUrl;
    }

    @PrePersist
    public void prePersist() {
        this.cadastradoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public LocalDateTime getCadastradoEm() {
        return cadastradoEm;
    }
}