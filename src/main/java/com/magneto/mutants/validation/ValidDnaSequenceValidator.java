package com.magneto.mutants.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidDnaSequenceValidator implements ConstraintValidator<ValidDnaSequence, String[]> {
    @Override
    public boolean isValid(String[] valor, ConstraintValidatorContext context) {
        if (valor == null || valor.length == 0) return false;
        int n = valor.length;
        for (int i = 0; i < n; i++) {
            String fila = valor[i];
            if (fila == null) return false;
            fila = fila.trim().toUpperCase();
            if (fila.length() != n) return false;
            for (int j = 0; j < n; j++) {
                char caracter = fila.charAt(j);
                if (caracter != 'A' && caracter != 'T' && caracter != 'C' && caracter != 'G') return false;
            }
            valor[i] = fila;
        }
        return true;
    }
}
