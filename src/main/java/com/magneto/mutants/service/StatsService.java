package com.magneto.mutants.service;

import com.magneto.mutants.dto.StatsResponse;
import com.magneto.mutants.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    private final DnaRecordRepository repository;

    public StatsService(DnaRecordRepository repository) {
        this.repository = repository;
    }

    public StatsResponse obtenerEstadisticas() {
        long mutantes = repository.countByMutanteTrue();
        long humanos = repository.countByMutanteFalse();
        double ratio = humanos == 0 ? 0.0 : ((double) mutantes) / humanos;
        return new StatsResponse(mutantes, humanos, ratio);
    }
}
