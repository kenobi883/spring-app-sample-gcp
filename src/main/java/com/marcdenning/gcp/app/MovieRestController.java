package com.marcdenning.gcp.app;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/movies")
public class MovieRestController {

    private final MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(movieRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Movie> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(movieRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new));
    }
}
