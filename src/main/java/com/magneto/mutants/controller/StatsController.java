package com.magneto.mutants.controller;

import com.magneto.mutants.dto.StatsResponse;
import com.magneto.mutants.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class StatsController {
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @Operation(summary = "Devuelve estadísticas de ADN analizados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estadísticas actuales")
    })
    @GetMapping("/stats")
    public StatsResponse estadisticas() {
        return statsService.obtenerEstadisticas();
    }
}
