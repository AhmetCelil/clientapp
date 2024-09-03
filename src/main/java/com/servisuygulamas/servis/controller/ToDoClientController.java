package com.servisuygulamas.servis.controller;

import com.servisuygulamas.servis.dto.ToDoDTO;
import com.servisuygulamas.servis.servis.ToDoClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class ToDoClientController {

    @Autowired
    private ToDoClientService toDoClientService;

    @PostMapping("/ekle")
    public ToDoDTO createToDoDTO(@RequestBody ToDoDTO toDoDTO) {
        return toDoClientService.createToDo(toDoDTO);
    }
/*
    @GetMapping("/candidates/{id}")
    public Mono<CandidateDTO> getCandidate(@PathVariable Long id) {
        return cvHavuzuClientService.getCandidate(id);
    }

    @DeleteMapping("/candidates/{id}")
    public Mono<Void> deleteCandidate(@PathVariable String id) {
        return cvHavuzuClientService.deleteCandidate(id);
    }
    @GetMapping("/candidates")
    public Mono<List<CandidateDTO>> getAllCandidates() {
        return cvHavuzuClientService.getAllCandidates();
    }*/

}
