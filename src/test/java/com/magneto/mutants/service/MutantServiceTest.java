package com.magneto.mutants.service;

import com.magneto.mutants.entity.DnaRecord;
import com.magneto.mutants.repository.DnaRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MutantServiceTest {

    @Mock
    private DnaRecordRepository dnaRecordRepository;

    @Mock
    private MutantDetector mutantDetector;

    @InjectMocks
    private MutantService mutantService;

    private String[] mutantDna;
    private String[] humanDna;

    @BeforeEach
    void setUp() {
        mutantDna = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        humanDna = new String[]{"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
    }

    @Test
    @DisplayName("should return true when DNA is mutant and not in cache")
    void isMutant_whenMutantAndNotInCache_shouldReturnTrue() {
        when(dnaRecordRepository.findByDnaHash(anyString())).thenReturn(Optional.empty());
        when(mutantDetector.isMutant(mutantDna)).thenReturn(true);

        boolean result = mutantService.isMutant(mutantDna);

        assertTrue(result);
        verify(dnaRecordRepository, times(1)).findByDnaHash(anyString());
        verify(mutantDetector, times(1)).isMutant(mutantDna);
        verify(dnaRecordRepository, times(1)).save(any(DnaRecord.class));
    }

    @Test
    @DisplayName("should return false when DNA is human and not in cache")
    void isMutant_whenHumanAndNotInCache_shouldReturnFalse() {
        when(dnaRecordRepository.findByDnaHash(anyString())).thenReturn(Optional.empty());
        when(mutantDetector.isMutant(humanDna)).thenReturn(false);

        boolean result = mutantService.isMutant(humanDna);

        assertFalse(result);
        verify(dnaRecordRepository, times(1)).findByDnaHash(anyString());
        verify(mutantDetector, times(1)).isMutant(humanDna);
        verify(dnaRecordRepository, times(1)).save(any(DnaRecord.class));
    }

    @Test
    @DisplayName("should return true from cache when DNA is mutant")
    void isMutant_whenMutantInCache_shouldReturnTrueFromCache() {
        DnaRecord cachedRecord = new DnaRecord("somehash", true);
        when(dnaRecordRepository.findByDnaHash(anyString())).thenReturn(Optional.of(cachedRecord));

        boolean result = mutantService.isMutant(mutantDna);

        assertTrue(result);
        verify(dnaRecordRepository, times(1)).findByDnaHash(anyString());
        verify(mutantDetector, never()).isMutant(any());
        verify(dnaRecordRepository, never()).save(any());
    }

    @Test
    @DisplayName("should return false from cache when DNA is human")
    void isMutant_whenHumanInCache_shouldReturnFalseFromCache() {
        DnaRecord cachedRecord = new DnaRecord("somehash", false);
        when(dnaRecordRepository.findByDnaHash(anyString())).thenReturn(Optional.of(cachedRecord));

        boolean result = mutantService.isMutant(humanDna);

        assertFalse(result);
        verify(dnaRecordRepository, times(1)).findByDnaHash(anyString());
        verify(mutantDetector, never()).isMutant(any());
        verify(dnaRecordRepository, never()).save(any());
    }
}
