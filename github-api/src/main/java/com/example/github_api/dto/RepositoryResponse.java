package com.example.github_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RepositoryResponse {
    @JsonProperty("name")
    String repositoryName;

    @JsonProperty("owner")
    Owner owner;

    List<BranchResponse> branches;

    @JsonProperty("fork")
    boolean fork;

    @JsonIgnore
    public String getOwnerLogin() {
        return owner != null ? owner.getLogin() : null;
    }
}
