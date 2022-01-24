package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO pelicula);

    List<PeliculaDTO> getAllMovies();

    void delete(Long id);

    PeliculaDTO putMovie(Long id, PeliculaDTO edit);

    List<PeliculaBasicDTO> getAllBasics();

    List<PeliculaDTO> getByFilters(String title, Set<Long> genres, String order);
}
