package com.magneto.mutants.service;

import com.magneto.mutants.entity.DnaRecord;
import com.magneto.mutants.exception.DnaHashCalculationException;
import com.magneto.mutants.repository.DnaRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MutantService {

    private final DnaRecordRepository dnaRecordRepository;
    private final MutantDetector mutantDetector;

    @Transactional
    public boolean isMutant(String[] dna) {
        String dnaHash = calculateDnaHash(dna);

        Optional<DnaRecord> existingRecord = dnaRecordRepository.findByDnaHash(dnaHash);
        if (existingRecord.isPresent()) {
            return existingRecord.get().isMutant();
        }

        boolean isMutant = mutantDetector.isMutant(dna);

        DnaRecord newRecord = new DnaRecord(dnaHash, isMutant);
        dnaRecordRepository.save(newRecord);

        return isMutant;
    }

    private String calculateDnaHash(String[] dna) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String dnaString = String.join("", dna);
            byte[] hash = digest.digest(dnaString.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new DnaHashCalculationException("Error calculating SHA-256 hash", e);
        }
    }
}