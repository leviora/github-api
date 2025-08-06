package com.example.github_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GithubIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void happyPath_shouldReturnRepositoriesWithBranches() throws Exception {
        mockMvc.perform(get("/api/github/leviora")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$[0].name", not(emptyOrNullString())))
                .andExpect(jsonPath("$[0].ownerLogin", is("leviora")))
                .andExpect(jsonPath("$[0].branches", not(empty())))
                .andExpect(jsonPath("$[0].branches[0].name", not(emptyOrNullString())))
                .andExpect(jsonPath("$[0].branches[0].lastCommitSha", not(emptyOrNullString())));
    }

    @Test
    void userNotFound_shouldReturn404() throws Exception {
        mockMvc.perform(get("/api/github/nonexistentuser123")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", containsString("User not found")));
    }

}
