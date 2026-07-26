package br.com.filamed.medico.service;

import br.com.filamed.especialidade.entity.Especialidade;
import br.com.filamed.especialidade.repository.EspecialidadeRepository;
import br.com.filamed.medico.dto.request.CadastroMedicoRequest;
import br.com.filamed.medico.dto.response.MedicoResponse;
import br.com.filamed.medico.entity.Medico;
import br.com.filamed.medico.mapper.MedicoMapper;
import br.com.filamed.medico.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final EspecialidadeRepository especialidadeRepository;

    public MedicoService(
            MedicoRepository medicoRepository,
            EspecialidadeRepository especialidadeRepository
    ) {
        this.medicoRepository = medicoRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    public MedicoResponse cadastrar(CadastroMedicoRequest request) {

        if (medicoRepository.existsByCrmIgnoreCase(request.crm())) {
            throw new IllegalArgumentException("Já existe um médico com esse CRM.");
        }

        Especialidade especialidade = especialidadeRepository
                .findById(request.especialidadeId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Especialidade não encontrada.")
                );

        Medico medico = new Medico(
                request.nome(),
                request.crm(),
                request.telefone(),
                request.email(),
                especialidade
        );

        return MedicoMapper.paraResponse(
                medicoRepository.save(medico)
        );
    }

    public List<MedicoResponse> listar() {
        return medicoRepository.findAll()
                .stream()
                .map(MedicoMapper::paraResponse)
                .toList();
    }

    public MedicoResponse buscarPorId(Long id) {

        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Médico não encontrado.")
                );

        return MedicoMapper.paraResponse(medico);
    }

    public MedicoResponse atualizar(
            Long id,
            CadastroMedicoRequest request
    ) {

        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Médico não encontrado.")
                );

        if (medicoRepository.existsByCrmIgnoreCaseAndIdNot(request.crm(), id)) {
            throw new IllegalArgumentException("Já existe um médico com esse CRM.");
        }

        Especialidade especialidade = especialidadeRepository
                .findById(request.especialidadeId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Especialidade não encontrada.")
                );

        medico.setNome(request.nome());
        medico.setCrm(request.crm());
        medico.setTelefone(request.telefone());
        medico.setEmail(request.email());
        medico.setEspecialidade(especialidade);

        return MedicoMapper.paraResponse(
                medicoRepository.save(medico)
        );
    }

    public void excluir(Long id) {

        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Médico não encontrado.")
                );

        medicoRepository.delete(medico);
    }
}