package com.servisuygulamas.servis.servis;

import com.servisuygulamas.servis.dto.CandidateDTO;
import com.servisuygulamas.servis.model.Candidate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.time.Duration;

@Service
public class CvHavuzuClientService {

    private final WebClient.Builder webClientBuilder;

    public CvHavuzuClientService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @CircuitBreaker(name = "getCandidateCircuitBreaker", fallbackMethod = "getCandidateFallback")
    public Mono<CandidateDTO> getCandidate(Long id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/candidates/{id}", id)
                .retrieve()
                .bodyToMono(CandidateDTO.class)
                .timeout(Duration.ofMillis(5)) // Timeout ayarı
                .onErrorResume(e -> {
                    // Timeout veya diğer hatalarda fallback işlemi
                    return Mono.empty(); // veya uygun bir fallback
                });
    }

    @CircuitBreaker(name = "getAllCandidatesCircuitBreaker", fallbackMethod = "getAllCandidatesFallback")
    public Flux<CandidateDTO> getAllCandidates() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/candidates/all-candidates")
                .retrieve()
                .bodyToFlux(CandidateDTO.class);
    }

    public Mono<Candidate> createCandidate(Candidate candidate) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8080/api/candidates")
                .body(Mono.just(candidate), Candidate.class)
                .retrieve()
                .bodyToMono(Candidate.class);
    }

    public Mono<Void> deleteCandidate(String userID) {
        return webClientBuilder.build()
                .delete()
                .uri("http://localhost:8080/api/candidates/delete/{id}", userID)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<String> fallbackMethod(Throwable throwable) {
        return Mono.just("Fallback response due to: " + throwable.getMessage());
    }

    public Mono<CandidateDTO> getCandidateFallback(Long id, Throwable throwable) {
        // Bu metot, Circuit Breaker devreye girdiğinde çağrılır
        // Burada istediğiniz bir varsayılan yanıt döndürebilirsiniz
        return Mono.just(new CandidateDTO()); // Örneğin, boş bir CandidateDTO döndürebilirsiniz
    }

    public Flux<CandidateDTO> getAllCandidatesFallback(Throwable throwable) {
        // Bu metot, Circuit Breaker devreye girdiğinde çağrılır
        // Burada istediğiniz bir varsayılan yanıt döndürebilirsiniz
        return Flux.empty(); // Örneğin, boş bir Flux döndürebilirsiniz
    }
}
