package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.PeliculaDTO;

import java.util.List;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO pelicula);

    List<PeliculaDTO> getAllPelicula();

    void delete(Long id);
}
