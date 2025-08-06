package com.example.github_api.service.impl;

import com.example.github_api.dto.BranchResponse;
import com.example.github_api.dto.RepositoryResponse;
import com.example.github_api.exception.UserNotFoundException;
import com.example.github_api.service.GithubService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubServiceImpl implements GithubService {

private final RestTemplate restTemplate;
private static final String GITHUB_API_URL = "https://api.github.com";

    public GithubServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

@Override
public List<RepositoryResponse> getUserRepositories(String username) {
    String url = GITHUB_API_URL + "/users/" + username + "/repos";

    try {
        RepositoryResponse[] repos = restTemplate.getForObject(url, RepositoryResponse[].class);

        if (repos == null || repos.length == 0) {
            return Collections.emptyList();
        }

        List<RepositoryResponse> filteredRepos = Arrays.stream(repos)
                .filter(repo -> !repo.isFork())
                .collect(Collectors.toList());

        for (RepositoryResponse repo : filteredRepos) {
            String branchesUrl = GITHUB_API_URL + "/repos/" + repo.getOwnerLogin() + "/" + repo.getRepositoryName() + "/branches";
            BranchResponse[] branches = restTemplate.getForObject(branchesUrl, BranchResponse[].class);

            if (branches != null) {
                repo.setBranches(Arrays.asList(branches));
            } else {
                repo.setBranches(Collections.emptyList());
            }
        }

        return filteredRepos;

    } catch (HttpClientErrorException.NotFound e) {
        throw new UserNotFoundException(username);
    }
}

}
