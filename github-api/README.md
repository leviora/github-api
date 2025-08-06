REST API application that fetches information about GitHub 
user repositories and their branches. 
Data is retrieved from the official GitHub API, 
forked repositories are filtered out, 
and the result is returned in JSON format.

Technologies:

-Java 21

-Spring Boot 3.5.4

-RestTemplate for GitHub API communication

-Lombok

-JUnit 5, MockMvc (integration tests)

How to run
1.  Clone the repository:
    git clone <REPOSITORY_URL>
2. Build the project:
   ./mvnw clean install
3. Run the application:
   ./mvnw spring-boot:run
4. The API is available at:
   http://localhost:8080/api/github/{username}

Example request
GET /api/github/leviora

Example response

[
{
"repositoryName": "example-repo",
"owner": {
"login": "leviora"
},
"branches": [
{
"name": "main",
"lastCommitSha": "abcdef123456"
}
],
"fork": false
}
]

Tests
The project includes integration tests verifying 
the correctness of the endpoint and error handling (e.g., 404).