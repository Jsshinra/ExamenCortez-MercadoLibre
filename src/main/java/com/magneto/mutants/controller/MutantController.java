package com.magneto.mutants.controller;

import com.magneto.mutants.dto.DnaRequest;
import com.magneto.mutants.service.MutantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
@RequiredArgsConstructor
@Validated
public class MutantController {

    private final MutantService mutantService;

    @Operation(summary = "Verify if a DNA sequence belongs to a mutant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DnaRequest.class),
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "  \"dna\": [\n" +
                                            "    \"ATGCGA\",\n" +
                                            "    \"CAGTGC\",\n" +
                                            "    \"TTATGT\",\n" +
                                            "    \"AGAAAG\",\n" +
                                            "    \"CCCCTA\",\n" +
                                            "    \"TCACTG\"\n" +
                                            "  ]\n" +
                                            "}"
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The DNA sequence belongs to a mutant"),
            @ApiResponse(responseCode = "403", description = "The DNA sequence belongs to a human"),
            @ApiResponse(responseCode = "400", description = "Invalid DNA sequence provided")
    })
    @PostMapping
    public ResponseEntity<Void> isMutant(@Valid @RequestBody DnaRequest dnaRequest) {
        boolean isMutant = mutantService.isMutant(dnaRequest.getDna());
        return isMutant ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}