package com.servisuygulamas.servis.controller;

import com.servisuygulamas.servis.dto.CandidateDTO;
import com.servisuygulamas.servis.model.Candidate;
import com.servisuygulamas.servis.servis.CvHavuzuClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/cv")
public class CvHavuzuClientController {

    @Autowired
    private CvHavuzuClientService cvHavuzuClientService;

    @GetMapping("/candidates/{id}")
    public Mono<CandidateDTO> getCandidate(@PathVariable Long id) {
        return cvHavuzuClientService.getCandidate(id);
    }

    @GetMapping("/candidates")
    public List<CandidateDTO> getCandidates() {
        List<CandidateDTO> candidates = (List<CandidateDTO>) cvHavuzuClientService.getAllCandidates();
        return candidates;
    }

    @PostMapping("/candidates")
    public Mono<Candidate> createCandidate(@RequestBody Candidate candidate) {
        return cvHavuzuClientService.createCandidate(candidate);
    }

}
