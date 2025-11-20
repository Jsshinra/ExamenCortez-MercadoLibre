package com.magneto.mutants.service;

import com.magneto.mutants.entity.DnaRecord;
import com.magneto.mutants.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@Service
public class MutantService {
    private final MutantDetector detectorMutante;
    private final DnaRecordRepository repositorio;

    public MutantService(MutantDetector detectorMutante, DnaRecordRepository repositorio) {
        this.detectorMutante = detectorMutante;
        this.repositorio = repositorio;
    }

    @Transactional
    public boolean analizarYGuardar(String[] adn) {
        String hash = calcularHashAdn(adn);
        return repositorio.findByHashAdn(hash)
                .map(DnaRecord::esMutante)
                .orElseGet(() -> {
                    boolean mutante = detectorMutante.esMutante(adn);
                    repositorio.save(new DnaRecord(hash, mutante));
                    return mutante;
                });
    }

    private String calcularHashAdn(String[] adn) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            for (String fila : adn) {
                String normalizada = fila == null ? "" : fila.trim().toUpperCase();
                digest.update(normalizada.getBytes(StandardCharsets.UTF_8));
                digest.update((byte) '\n');
            }
            byte[] hash = digest.digest();
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}
