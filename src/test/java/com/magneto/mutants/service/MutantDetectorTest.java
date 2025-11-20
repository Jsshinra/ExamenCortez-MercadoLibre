package com.magneto.mutants.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MutantDetectorTest {

    private final MutantDetector detector = new MutantDetector();

    @Test
    void mutantExampleReturnsTrue() {
        String[] dna = new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(detector.esMutante(dna));
    }

    @Test
    void humanExampleReturnsFalse() {
        String[] dna = new String[]{
                "ATGCGA",
                "CATTGC",
                "TTATGT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };
        assertFalse(detector.esMutante(dna));
    }

    @Test
    void detectsDiagonalAndHorizontalSequences() {
        String[] dna = new String[]{
                "AAAAGG", // horizontal AAAA
                "CAATGC",
                "TTATGT",
                "AGAAAG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(detector.esMutante(dna));
    }

    @Test
    void detectsAntiDiagonalAndVerticalSequences() {
        String[] dna = new String[]{
                "CCGCGT", // anti-diagonal T from (0,5) to (3,2), vertical C in col 0
                "CAGTTC",
                "CTTTGA",
                "CATTCG",
                "GGGGGG",
                "TACGAC"
        };
        assertTrue(detector.esMutante(dna));
    }

    @Test
    void singleSequenceIsNotMutant() {
        String[] dna = new String[]{
                "AAAA",   // only one sequence
                "CGTG",
                "TCAG",
                "GATC"
        };
        assertFalse(detector.esMutante(dna));
    }
}
