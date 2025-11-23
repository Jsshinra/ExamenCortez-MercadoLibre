package com.magneto.mutants.dto;

import com.magneto.mutants.validation.ValidDnaSequence;
import lombok.Data;

@Data
public class DnaRequest {

    @ValidDnaSequence
    private String[] dna;
}