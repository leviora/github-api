package com.example.github_api.service;

import com.example.github_api.dto.RepositoryResponse;

import java.util.List;

public interface GithubService {
    List<RepositoryResponse> getUserRepositories(String username);
}
