package br.com.filamed.especialidade.controller;

import br.com.filamed.especialidade.dto.request.CadastroEspecialidadeRequest;
import br.com.filamed.especialidade.dto.response.EspecialidadeResponse;
import br.com.filamed.especialidade.service.EspecialidadeService;
import br.com.filamed.exception.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
@Tag(
        name = "Especialidades",
        description = "Endpoints para cadastro e gerenciamento de especialidades médicas"
)
public class EspecialidadeController {

    private final EspecialidadeService service;

    public EspecialidadeController(EspecialidadeService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar especialidade",
            description = "Cadastra uma nova especialidade médica"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Especialidade cadastrada com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = EspecialidadeResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados enviados são inválidos",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Já existe uma especialidade com o mesmo nome",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<EspecialidadeResponse> cadastrar(
            @Valid @RequestBody CadastroEspecialidadeRequest request
    ) {
        EspecialidadeResponse response = service.cadastrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar especialidades",
            description = "Retorna todas as especialidades cadastradas"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Especialidades retornadas com sucesso"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<List<EspecialidadeResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar especialidade por ID",
            description = "Retorna uma especialidade pelo seu identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Especialidade encontrada",
                    content = @Content(
                            schema = @Schema(implementation = EspecialidadeResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Especialidade não encontrada",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<EspecialidadeResponse> buscarPorId(
            @Parameter(
                    description = "Identificador da especialidade",
                    example = "1",
                    required = true
            )
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar especialidade",
            description = "Atualiza os dados de uma especialidade existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Especialidade atualizada com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = EspecialidadeResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados enviados são inválidos",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Especialidade não encontrada",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Já existe outra especialidade com o mesmo nome",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<EspecialidadeResponse> atualizar(
            @Parameter(
                    description = "Identificador da especialidade",
                    example = "1",
                    required = true
            )
            @PathVariable Long id,

            @Valid
            @RequestBody CadastroEspecialidadeRequest request
    ) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir especialidade",
            description = "Exclui uma especialidade pelo seu identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Especialidade excluída com sucesso",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Especialidade não encontrada",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<Void> excluir(
            @Parameter(
                    description = "Identificador da especialidade",
                    example = "1",
                    required = true
            )
            @PathVariable Long id
    ) {
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}