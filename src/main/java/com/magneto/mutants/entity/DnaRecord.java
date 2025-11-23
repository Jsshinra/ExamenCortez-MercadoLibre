package com.magneto.mutants.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dna_records", indexes = {
        @Index(name = "idx_dna_hash", columnList = "dnaHash", unique = true),
        @Index(name = "idx_is_mutant", columnList = "isMutant")
})
public class DnaRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String dnaHash;

    @Column(nullable = false)
    private boolean isMutant;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public DnaRecord(String dnaHash, boolean isMutant) {
        this.dnaHash = dnaHash;
        this.isMutant = isMutant;
        this.createdAt = LocalDateTime.now();
    }
}