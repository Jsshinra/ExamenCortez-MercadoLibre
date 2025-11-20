package com.magneto.mutants.repository;

import com.magneto.mutants.entity.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {
    Optional<DnaRecord> findByHashAdn(String hashAdn);
    long countByMutanteTrue();
    long countByMutanteFalse();
}
