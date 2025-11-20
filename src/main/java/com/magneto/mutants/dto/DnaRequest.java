package com.magneto.mutants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.magneto.mutants.validation.ValidDnaSequence;
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

    public String[] getAdn() {
        return adn;
    }

    public void setAdn(String[] adn) {
        this.adn = adn;
    }
}
