package br.com.filamed.consulta.service;

import br.com.filamed.consulta.dto.request.CadastroConsultaRequest;
import br.com.filamed.consulta.dto.response.ConsultaResponse;
import br.com.filamed.consulta.entity.Consulta;
import br.com.filamed.consulta.mapper.ConsultaMapper;
import br.com.filamed.consulta.repository.ConsultaRepository;
import br.com.filamed.medico.entity.Medico;
import br.com.filamed.medico.repository.MedicoRepository;
import br.com.filamed.paciente.entity.Paciente;
import br.com.filamed.paciente.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(
            ConsultaRepository consultaRepository,
            PacienteRepository pacienteRepository,
            MedicoRepository medicoRepository
    ) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public ConsultaResponse cadastrar(CadastroConsultaRequest request) {

        Paciente paciente = pacienteRepository
                .findById(request.pacienteId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Paciente não encontrado.")
                );

        Medico medico = medicoRepository
                .findById(request.medicoId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Médico não encontrado.")
                );

        Consulta consulta = new Consulta(
                paciente,
                medico,
                request.dataHora(),
                request.observacao()
        );

        return ConsultaMapper.paraResponse(
                consultaRepository.save(consulta)
        );
    }

    public List<ConsultaResponse> listar() {
        return consultaRepository.findAll()
                .stream()
                .map(ConsultaMapper::paraResponse)
                .toList();
    }

    public ConsultaResponse buscarPorId(Long id) {

        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Consulta não encontrada.")
                );

        return ConsultaMapper.paraResponse(consulta);
    }

    public ConsultaResponse atualizar(
            Long id,
            CadastroConsultaRequest request
    ) {

        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Consulta não encontrada.")
                );

        Paciente paciente = pacienteRepository
                .findById(request.pacienteId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Paciente não encontrado.")
                );

        Medico medico = medicoRepository
                .findById(request.medicoId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Médico não encontrado.")
                );

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setDataHora(request.dataHora());
        consulta.setObservacao(request.observacao());

        return ConsultaMapper.paraResponse(
                consultaRepository.save(consulta)
        );
    }

    public void excluir(Long id) {

        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Consulta não encontrada.")
                );

        consultaRepository.delete(consulta);
    }
}