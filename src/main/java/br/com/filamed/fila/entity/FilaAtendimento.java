package br.com.filamed.fila.entity;

import br.com.filamed.paciente.entity.Paciente;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fila_atendimento")
public class FilaAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private Integer senha;

    @Column(nullable = false)
    private LocalDateTime entradaFila;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusFila status;

    public FilaAtendimento() {
    }

    public FilaAtendimento(Paciente paciente,
                           Integer senha,
                           LocalDateTime entradaFila,
                           StatusFila status) {

        this.paciente = paciente;
        this.senha = senha;
        this.entradaFila = entradaFila;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Integer getSenha() {
        return senha;
    }

    public void setSenha(Integer senha) {
        this.senha = senha;
    }

    public LocalDateTime getEntradaFila() {
        return entradaFila;
    }

    public void setEntradaFila(LocalDateTime entradaFila) {
        this.entradaFila = entradaFila;
    }

    public StatusFila getStatus() {
        return status;
    }

    public void setStatus(StatusFila status) {
        this.status = status;
    }
}