package com.magneto.mutants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsResponse {
    @JsonProperty("count_mutant_dna")
    private long countMutantDna;

    @JsonProperty("count_human_dna")
    private long countHumanDna;

    @JsonProperty("ratio")
    private double ratio;

    public StatsResponse() {}

    public StatsResponse(long countMutantDna, long countHumanDna, double ratio) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        this.ratio = ratio;
    }

    public long getCountMutantDna() {
        return countMutantDna;
    }

    public long getCountHumanDna() {
        return countHumanDna;
    }

    public double getRatio() {
        return ratio;
    }
}
