package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO pelicula);

    List<PeliculaDTO> getAllPelicula();

    void delete(Long id);

    PeliculaDTO putPelicula(Long id, PeliculaDTO edit);

    List<PeliculaBasicDTO> getAllBasics();
}
