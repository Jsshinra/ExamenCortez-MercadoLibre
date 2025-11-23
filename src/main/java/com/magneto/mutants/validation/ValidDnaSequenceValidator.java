package com.magneto.mutants.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidDnaSequenceValidator implements ConstraintValidator<ValidDnaSequence, String[]> {

    private static final Pattern DNA_PATTERN = Pattern.compile("^[ATCG]+$");
    private static final int MIN_SIZE = 4;

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {
        if (dna == null || dna.length < MIN_SIZE) {
            return false; // Must be at least 4x4
        }

        final int n = dna.length;

        for (String row : dna) {
            if (row == null || row.length() != n) {
                return false; // Not a square matrix
            }
            if (!DNA_PATTERN.matcher(row).matches()) {
                return false; // Contains invalid characters
            }
        }

        return true;
    }
}