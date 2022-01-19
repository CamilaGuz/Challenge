package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.PersonajeDTO;

import java.util.List;

public interface PersonajeService {
    PersonajeDTO save(PersonajeDTO dto);

    List<PersonajeDTO> getAllPersonajes();

    void delete(Long id);

    PersonajeDTO putPersonaje(Long id, PersonajeDTO edit);
}
