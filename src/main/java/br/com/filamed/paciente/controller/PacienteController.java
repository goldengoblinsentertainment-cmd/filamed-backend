package br.com.filamed.paciente.controller;

import br.com.filamed.exception.ApiErrorResponse;
import br.com.filamed.paciente.dto.AtualizacaoPacienteRequest;
import br.com.filamed.paciente.dto.CadastroPacienteRequest;
import br.com.filamed.paciente.dto.response.PacienteResponse;
import br.com.filamed.paciente.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Pacientes",
        description = "Endpoints responsáveis pelo cadastro, consulta, atualização e exclusão de pacientes."
)
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Operation(
            summary = "Cadastrar paciente",
            description = """
                    Cadastra um novo paciente no sistema.

                    O CPF deve ser único. Caso já exista um paciente cadastrado
                    com o mesmo CPF, a API retornará o status HTTP 409.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Paciente cadastrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos ou campos obrigatórios não preenchidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Já existe um paciente cadastrado com o CPF informado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponse cadastrar(
            @Valid
            @RequestBody
            CadastroPacienteRequest request
    ) {
        return pacienteService.cadastrar(request);
    }

    @Operation(
            summary = "Listar pacientes",
            description = "Retorna todos os pacientes cadastrados no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de pacientes retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = PacienteResponse.class
                                    )
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    @GetMapping
    public List<PacienteResponse> listarTodos() {
        return pacienteService.listarTodos();
    }

    @Operation(
            summary = "Buscar paciente por ID",
            description = "Retorna os dados de um paciente a partir do seu identificador."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public PacienteResponse buscarPorId(
            @Parameter(
                    description = "Identificador único do paciente",
                    example = "1",
                    required = true
            )
            @PathVariable
            Long id
    ) {
        return pacienteService.buscarPorId(id);
    }

    @Operation(
            summary = "Atualizar paciente",
            description = """
                    Atualiza os dados de um paciente existente.

                    O paciente deve existir e o CPF informado não pode pertencer
                    a outro paciente cadastrado.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente atualizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos ou campos obrigatórios não preenchidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "O CPF informado já pertence a outro paciente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public PacienteResponse atualizar(
            @Parameter(
                    description = "Identificador único do paciente",
                    example = "1",
                    required = true
            )
            @PathVariable
            Long id,

            @Valid
            @RequestBody
            AtualizacaoPacienteRequest request
    ) {
        return pacienteService.atualizar(id, request);
    }

    @Operation(
            summary = "Excluir paciente",
            description = "Exclui permanentemente um paciente cadastrado no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Paciente excluído com sucesso",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @Parameter(
                    description = "Identificador único do paciente",
                    example = "1",
                    required = true
            )
            @PathVariable
            Long id
    ) {
        pacienteService.excluir(id);
    }
}