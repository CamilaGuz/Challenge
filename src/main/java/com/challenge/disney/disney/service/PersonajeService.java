package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.PersonajeBasicDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;

import java.util.List;
import java.util.Set;

public interface PersonajeService {

    PersonajeDTO save(PersonajeDTO dto);

    List<PersonajeDTO> getAllCharacters();

    void delete(Long id);

    PersonajeDTO putCharacter(Long id, PersonajeDTO edit);

    List<PersonajeBasicDTO> getAllBasics();

    PersonajeDTO getDetails(Long id);

    List<PersonajeDTO> getByFilters(String name, Integer age, Set<Long> idMovie);
}
