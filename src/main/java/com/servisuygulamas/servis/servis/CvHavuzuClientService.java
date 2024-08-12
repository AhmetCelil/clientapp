package com.servisuygulamas.servis.servis;

import com.servisuygulamas.servis.dto.CandidateDTO;
import com.servisuygulamas.servis.dto.CandidateListDTO;
import com.servisuygulamas.servis.model.Candidate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CvHavuzuClientService {

    private final WebClient.Builder webClientBuilder;

    public CvHavuzuClientService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }



    public Mono<CandidateDTO> getCandidate(Long id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/candidates/{id}", id)
                .retrieve()
                .bodyToMono(CandidateDTO.class);
    }

    public Mono<List<CandidateListDTO>> getAllCandidates() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/candidates")
                .retrieve()
                .bodyToFlux(CandidateListDTO.class)
                .collectList();
    }


    public Mono<Candidate> createCandidate(Candidate candidate) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8080/api/candidates")
                .body(Mono.just(candidate), Candidate.class)
                .retrieve()
                .bodyToMono(Candidate.class);
    }



}
