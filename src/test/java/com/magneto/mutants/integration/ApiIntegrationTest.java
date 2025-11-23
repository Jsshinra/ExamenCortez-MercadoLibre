package com.magneto.mutants.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magneto.mutants.dto.DnaRequest;
import com.magneto.mutants.repository.DnaRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DnaRecordRepository dnaRecordRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        dnaRecordRepository.deleteAll();
    }

    @Test
    @DisplayName("POST /mutant should return 200 OK for a mutant DNA")
    void postMutant_whenDnaIsMutant_shouldReturnOk() throws Exception {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        DnaRequest request = new DnaRequest();
        request.setDna(dna);

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /mutant should return 403 Forbidden for a human DNA")
    void postMutant_whenDnaIsHuman_shouldReturnForbidden() throws Exception {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        DnaRequest request = new DnaRequest();
        request.setDna(dna);

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("POST /mutant should return 400 Bad Request for invalid DNA")
    void postMutant_whenDnaIsInvalid_shouldReturnBadRequest() throws Exception {
        String[] dna = {"ATGC", "CAGT", "TTAT"}; // Not square
        DnaRequest request = new DnaRequest();
        request.setDna(dna);

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /stats should return correct statistics")
    void getStats_shouldReturnCorrectStatistics() throws Exception {
        // Submit a mutant DNA
        String[] mutantDna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        DnaRequest mutantRequest = new DnaRequest();
        mutantRequest.setDna(mutantDna);
        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mutantRequest)));

        // Submit a human DNA
        String[] humanDna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        DnaRequest humanRequest = new DnaRequest();
        humanRequest.setDna(humanDna);
        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(humanRequest)));

        // Check stats
        mockMvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna", is(1)))
                .andExpect(jsonPath("$.count_human_dna", is(1)))
                .andExpect(jsonPath("$.ratio", is(1.0)));
    }
}