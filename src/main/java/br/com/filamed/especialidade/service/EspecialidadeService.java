package br.com.filamed.especialidade.service;

import br.com.filamed.especialidade.dto.request.CadastroEspecialidadeRequest;
import br.com.filamed.especialidade.dto.response.EspecialidadeResponse;
import br.com.filamed.especialidade.entity.Especialidade;
import br.com.filamed.especialidade.mapper.EspecialidadeMapper;
import br.com.filamed.especialidade.repository.EspecialidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeService {

    private final EspecialidadeRepository repository;

    public EspecialidadeService(EspecialidadeRepository repository) {
        this.repository = repository;
    }

    public EspecialidadeResponse cadastrar(CadastroEspecialidadeRequest request) {

        if (repository.existsByNomeIgnoreCase(request.nome())) {
            throw new IllegalArgumentException("Já existe uma especialidade com esse nome.");
        }

        Especialidade especialidade = new Especialidade(
                request.nome(),
                request.descricao()
        );

        return EspecialidadeMapper.paraResponse(
                repository.save(especialidade)
        );
    }

    public List<EspecialidadeResponse> listar() {
        return repository.findAll()
                .stream()
                .map(EspecialidadeMapper::paraResponse)
                .toList();
    }

    public EspecialidadeResponse buscarPorId(Long id) {

        Especialidade especialidade = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Especialidade não encontrada."));

        return EspecialidadeMapper.paraResponse(especialidade);
    }

    public EspecialidadeResponse atualizar(
            Long id,
            CadastroEspecialidadeRequest request
    ) {

        Especialidade especialidade = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Especialidade não encontrada."));

        if (repository.existsByNomeIgnoreCaseAndIdNot(request.nome(), id)) {
            throw new IllegalArgumentException("Já existe uma especialidade com esse nome.");
        }

        especialidade.setNome(request.nome());
        especialidade.setDescricao(request.descricao());

        return EspecialidadeMapper.paraResponse(
                repository.save(especialidade)
        );
    }

    public void excluir(Long id) {

        Especialidade especialidade = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Especialidade não encontrada."));

        repository.delete(especialidade);
    }
}