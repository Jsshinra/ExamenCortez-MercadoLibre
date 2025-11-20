package com.magneto.mutants.controller;

import com.magneto.mutants.dto.DnaRequest;
import com.magneto.mutants.service.MutantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Validated
public class MutantController {
    private final MutantService mutantService;

    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @Operation(summary = "Detecta si un ADN pertenece a un mutante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mutante detectado"),
            @ApiResponse(responseCode = "403", description = "No mutante"),
            @ApiResponse(responseCode = "400", description = "Entrada inválida",
                    content = @Content(schema = @Schema(implementation = Object.class)))
    })
    @PostMapping("/mutant")
    public ResponseEntity<Void> esMutante(@Valid @RequestBody DnaRequest request) {
        boolean mutante = mutantService.analizarYGuardar(request.getAdn());
        return mutante ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
