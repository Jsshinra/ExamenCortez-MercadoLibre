package com.magneto.mutants.service;

import org.springframework.stereotype.Component;

@Component
public class MutantDetector {

    public boolean esMutante(String[] adn) {
        char[][] matriz = aMatriz(adn);
        int n = matriz.length;
        int encontradas = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char base = matriz[i][j];
                if (j + 3 < n && base == matriz[i][j + 1] && base == matriz[i][j + 2] && base == matriz[i][j + 3]) {
                    if (++encontradas > 1) return true;
                }
                if (i + 3 < n && base == matriz[i + 1][j] && base == matriz[i + 2][j] && base == matriz[i + 3][j]) {
                    if (++encontradas > 1) return true;
                }
                if (i + 3 < n && j + 3 < n && base == matriz[i + 1][j + 1] && base == matriz[i + 2][j + 2] && base == matriz[i + 3][j + 3]) {
                    if (++encontradas > 1) return true;
                }
                if (i + 3 < n && j - 3 >= 0 && base == matriz[i + 1][j - 1] && base == matriz[i + 2][j - 2] && base == matriz[i + 3][j - 3]) {
                    if (++encontradas > 1) return true;
                }
            }
        }
        return false;
    }

    private char[][] aMatriz(String[] adn) {
        int n = adn.length;
        char[][] matriz = new char[n][n];
        for (int i = 0; i < n; i++) {
            String fila = adn[i].trim().toUpperCase();
            for (int j = 0; j < n; j++) {
                matriz[i][j] = fila.charAt(j);
            }
        }
        return matriz;
    }
}
