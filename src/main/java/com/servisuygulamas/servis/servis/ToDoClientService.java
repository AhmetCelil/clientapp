package com.servisuygulamas.servis.servis;

import com.servisuygulamas.servis.dto.ToDoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class ToDoClientService {

    private final WebClient webClient;

    public ToDoClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build(); // Base URL'i belirtin
    }

    public ToDoDTO createToDo(ToDoDTO toDoDTO) {
        return webClient.post()
                .uri("/api/to_do/ekle-dto") // API URL'sini burada belirtin
                .body(Mono.just(toDoDTO), ToDoDTO.class)
                .retrieve()
                .bodyToMono(ToDoDTO.class)
                .block(); // bloklama gerekli değilse, 'block()' çağrısını kaldırabilirsiniz
    }
/*
    public Mono<CandidateDTO> getCandidate(Long id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/candidates/{id}", id)
                .retrieve()
                .bodyToMono(CandidateDTO.class);
    }

    public Mono<List<CandidateDTO>> getAllCandidates() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/candidates/all-candidates")
                .retrieve()
                .bodyToFlux(CandidateDTO.class)
                .collectList();

    }

    public Mono<Void> deleteCandidate(String userID) {
        return webClientBuilder.build()
                .delete()
                .uri("http://localhost:8080/api/candidates/delete/{id}", userID)
                .retrieve()
                .bodyToMono(Void.class);
    }*/
}