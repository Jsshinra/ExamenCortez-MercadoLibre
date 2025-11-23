package com.magneto.mutants.dto;

import com.magneto.mutants.validation.ValidDnaSequence;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(example = "{\n" +
        "  \"dna\": [\n" +
        "    \"ATGCGA\",\n" +
        "    \"CAGTGC\",\n" +
        "    \"TTATGT\",\n" +
        "    \"AGAAAG\",\n" +
        "    \"CCCCTA\",\n" +
        "    \"TCACTG\"\n" +
        "  ]\n" +
        "}")
public class DnaRequest {

    @ValidDnaSequence
    private String[] dna;
}