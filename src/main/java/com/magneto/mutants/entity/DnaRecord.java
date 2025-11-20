package com.magneto.mutants.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "dna_records",
        indexes = {
                @Index(name = "idx_dna_hash", columnList = "dna_hash", unique = true),
                @Index(name = "idx_is_mutant", columnList = "is_mutant")
        })
public class DnaRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dna_hash", nullable = false, length = 128)
    private String hashAdn;

    @Column(name = "is_mutant", nullable = false)
    private boolean mutante;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public DnaRecord() {}

    public DnaRecord(String hashAdn, boolean mutante) {
        this.hashAdn = hashAdn;
        this.mutante = mutante;
    }

    public Long getId() {
        return id;
    }

    public String getHashAdn() {
        return hashAdn;
    }

    public void setHashAdn(String hashAdn) {
        this.hashAdn = hashAdn;
    }

    public boolean esMutante() {
        return mutante;
    }

    public void setEsMutante(boolean mutante) {
        this.mutante = mutante;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
