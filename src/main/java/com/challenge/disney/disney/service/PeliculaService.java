package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;

import java.util.List;
import java.util.Set;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO movie);

    void delete(Long id);

    PeliculaDTO putMovie(Long id, PeliculaDTO edit);

    List<PeliculaDTO> getByFilters(String title, String image, String dateCreation, Set<Long> gender, String order);

    List<PeliculaBasicDTO> getAllBasics();
}
