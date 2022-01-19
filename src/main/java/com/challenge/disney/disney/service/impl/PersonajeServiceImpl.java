package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.PersonajeDTO;
import com.challenge.disney.disney.entity.PersonajeEntity;
import com.challenge.disney.disney.mapper.PersonajeMapper;
import com.challenge.disney.disney.repository.PersonajeRepository;
import com.challenge.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private PersonajeRepository personajeRepository;


    public PersonajeDTO save(PersonajeDTO dto){
        PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity personajeSaved = personajeRepository.save(personajeEntity);
        PersonajeDTO resultado = personajeMapper.personajeEntity2DTO(personajeSaved, false);


        return resultado;
    }



    public List<PersonajeDTO> getAllPersonajes(){
        List<PersonajeEntity> entityList = personajeRepository.findAll();
        List<PersonajeDTO> resultado = personajeMapper.personajeEntityList2DTOList(entityList, false);
        return resultado;
    }

    public void delete(Long id){
        personajeRepository.deleteById(id);
    }

    @Override
    public PersonajeDTO putPersonaje(Long id, PersonajeDTO edit) {

        PersonajeEntity savedPersonaje = thisPersonajeEdit(id);
        savedPersonaje.setPeso(edit.getPeso());
        savedPersonaje.setNombre(edit.getNombre());
        savedPersonaje.setImagen(edit.getImagen());
        savedPersonaje.setHistoria(edit.getHistoria());
        savedPersonaje.setEdad(edit.getEdad());


        PersonajeEntity putPersonaje = personajeRepository.save(savedPersonaje);
        PersonajeDTO savedDTO = personajeMapper.personajeEntity2DTO(putPersonaje, false);
        return savedDTO;
    }

    private PersonajeEntity thisPersonajeEdit(Long id) {
        Optional<PersonajeEntity> personajeEntity = personajeRepository.findById(id);
        if (!personajeEntity.isPresent()){

        }
        return personajeEntity.get();
    }


}
