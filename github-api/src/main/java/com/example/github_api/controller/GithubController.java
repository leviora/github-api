package com.example.github_api.controller;

import com.example.github_api.dto.RepositoryResponse;
import com.example.github_api.service.GithubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/github")
public class GithubController {
    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryResponse>> getUserRepos(@PathVariable String username) {
        List<RepositoryResponse> repos = githubService.getUserRepositories(username);

        return repos.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(repos);
    }



//    @GetMapping("/{username}")
//    public ResponseEntity<?> getUserRepos(@PathVariable String username) {
//        List<RepositoryResponse> repos = githubService.getUserRepositories(username);
//
//        if (repos.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(repos);
//    }
}

