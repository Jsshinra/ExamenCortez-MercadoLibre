package com.magneto.mutants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.magneto.mutants.validation.ValidDnaSequence;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public class DnaRequest {
    @NotEmpty(message = "dna must not be empty")
    @ValidDnaSequence
    @JsonProperty("dna")
    private String[] adn;

    public DnaRequest() {}

    public DnaRequest(String[] adn) {
        this.adn = adn;
    }
    @Schema(
            description = "Secuencia de ADN (matriz NxN) con solo caracteres A, T, C, G.",
            example = "["ATGCGA","CAGTGC","TTATGT","AGAAAG","CCCCTA","TCACTG"]",
            required = true
    )
    public static String[] getAdn(DnaRequest dnaRequest) {
        return dnaRequest.adn;
    }

    public void setAdn(String[] adn) {
        this.adn = adn;
    }
}
