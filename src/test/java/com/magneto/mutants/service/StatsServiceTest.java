package com.magneto.mutants.service;

import com.magneto.mutants.dto.StatsResponse;
import com.magneto.mutants.repository.DnaRecordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatsServiceTest {

    @Mock
    private DnaRecordRepository dnaRecordRepository;

    @InjectMocks
    private StatsService statsService;

    @Test
    @DisplayName("should return correct stats and ratio")
    void getStats_shouldReturnCorrectStatsAndRatio() {
        when(dnaRecordRepository.countByIsMutant(true)).thenReturn(40L);
        when(dnaRecordRepository.countByIsMutant(false)).thenReturn(100L);

        StatsResponse stats = statsService.getStats();

        assertEquals(40, stats.getCountMutantDna());
        assertEquals(100, stats.getCountHumanDna());
        assertEquals(0.4, stats.getRatio());
    }

    @Test
    @DisplayName("should return ratio of 0 when no mutants")
    void getStats_whenNoMutants_shouldReturnRatioZero() {
        when(dnaRecordRepository.countByIsMutant(true)).thenReturn(0L);
        when(dnaRecordRepository.countByIsMutant(false)).thenReturn(100L);

        StatsResponse stats = statsService.getStats();

        assertEquals(0, stats.getCountMutantDna());
        assertEquals(100, stats.getCountHumanDna());
        assertEquals(0.0, stats.getRatio());
    }

    @Test
    @DisplayName("should return ratio equal to mutant count when no humans")
    void getStats_whenNoHumans_shouldReturnRatioEqualToMutantCount() {
        when(dnaRecordRepository.countByIsMutant(true)).thenReturn(40L);
        when(dnaRecordRepository.countByIsMutant(false)).thenReturn(0L);

        StatsResponse stats = statsService.getStats();

        assertEquals(40, stats.getCountMutantDna());
        assertEquals(0, stats.getCountHumanDna());
        assertEquals(40.0, stats.getRatio());
    }

    @Test
    @DisplayName("should return ratio of 0 when no records exist")
    void getStats_whenNoRecords_shouldReturnRatioZero() {
        when(dnaRecordRepository.countByIsMutant(true)).thenReturn(0L);
        when(dnaRecordRepository.countByIsMutant(false)).thenReturn(0L);

        StatsResponse stats = statsService.getStats();

        assertEquals(0, stats.getCountMutantDna());
        assertEquals(0, stats.getCountHumanDna());
        assertEquals(0.0, stats.getRatio());
    }
}
