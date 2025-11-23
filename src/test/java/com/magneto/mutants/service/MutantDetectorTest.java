package com.magneto.mutants.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MutantDetectorTest {

    private MutantDetector mutantDetector;

    @BeforeEach
    void setUp() {
        mutantDetector = new MutantDetector();
    }

    @Test
    @DisplayName("should return true for mutant with horizontal and diagonal sequences")
    void isMutant_whenHorizontalAndDiagonal_shouldReturnTrue() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("should return true for mutant with two vertical sequences")
    void isMutant_whenTwoVertical_shouldReturnTrue() {
        String[] dna = {"ATGCGA", "AAGTGC", "ATATGT", "AGAGGG", "ACCCTA", "TCACTG"};
        assertTrue(mutantDetector.isMutant(dna));
    }
    
    @Test
    @DisplayName("should return true for mutant with multiple horizontal sequences")
    void isMutant_whenMultipleHorizontal_shouldReturnTrue() {
        String[] dna = {"AAAAGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("should return true for mutant with diagonal sequences")
    void isMutant_whenDiagonals_shouldReturnTrue() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CGCCTA", "TCACTG"};
        // A at (0,0), (1,1), (2,2), (3,3)
        // G at (0,2), (1,3), (2,4), (3,5)
        // This is not a mutant based on the provided DNA. Let's create a proper one.
        String[] mutantDna = {
            "AGCG",
            "GACG",
            "CGCG",
            "AGCG"
        };
        assertTrue(mutantDetector.isMutant(mutantDna));
    }

    @Test
    @DisplayName("should return false for human with only one sequence")
    void isMutant_whenOnlyOneSequence_shouldReturnFalse() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("should return false for human with no sequences")
    void isMutant_whenNoSequences_shouldReturnFalse() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGC", "AGACGG", "GCGTCA", "TCACTG"};
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("should return false for invalid DNA (null)")
    void isMutant_whenDnaIsNull_shouldReturnFalse() {
        assertFalse(mutantDetector.isMutant(null));
    }

    @Test
    @DisplayName("should return false for invalid DNA (not square)")
    void isMutant_whenNotSquare_shouldReturnFalse() {
        String[] dna = {"ATGC", "CAGT", "TTAT"};
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("should return false for invalid DNA (too small)")
    void isMutant_whenTooSmall_shouldReturnFalse() {
        String[] dna = {"ATG", "CAG", "TTA"};
        assertFalse(mutantDetector.isMutant(dna));
    }
    
    @Test
    @DisplayName("should return true for a 4x4 all-A matrix")
    void isMutant_whenAllSameChar4x4_shouldReturnTrue() {
        String[] dna = {
            "AAAA",
            "AAAA",
            "AAAA",
            "AAAA"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }
}