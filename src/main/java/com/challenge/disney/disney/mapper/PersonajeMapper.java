package com.challenge.disney.disney.mapper;

import com.challenge.disney.disney.dto.PersonajeBasicDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;
import com.challenge.disney.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {


    private PeliculaMapper peliculaMapper;

    public PersonajeMapper(@Autowired @Lazy PeliculaMapper peliculaMapper) {
        this.peliculaMapper = peliculaMapper;
    }

    //dto a entity

    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto){

        PersonajeEntity personajeEntity = new PersonajeEntity();

        personajeEntity.setAge(dto.getAge());
        personajeEntity.setHistory(dto.getHistory());
        personajeEntity.setImage(dto.getImage());
        personajeEntity.setName(dto.getName());
        personajeEntity.setWeight(dto.getWeight());

        return personajeEntity;
    }

    //entity a dto

    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean b) {

        PersonajeDTO dto = new PersonajeDTO();

        dto.setId(entity.getId());
        dto.setAge(entity.getAge());
        dto.setHistory(entity.getHistory());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setWeight(entity.getWeight());

        if (b){
            dto.setCharacterMovies(peliculaMapper.peliculaEntityList2DtoList(entity.getMovies(), false));
        }

        return dto;
    }

    //guardar en lista dto

    public List<PersonajeDTO>personajeEntityList2DTOList(List<PersonajeEntity> listaEntity, boolean b) {
        List<PersonajeDTO> dtoList = new ArrayList<>();
        for (PersonajeEntity entity : listaEntity) {
            dtoList.add(this.personajeEntity2DTO(entity, b));

        }
        return dtoList;
    }

    //trabajamos con la lista de personajes basic

    public List<PersonajeBasicDTO> personajeEntityList2DTOListBasic(List<PersonajeEntity> entityList) {

        List<PersonajeBasicDTO>dtoLista = new ArrayList<>();
        for(PersonajeEntity aux : entityList){
            dtoLista.add(this.personajeEntity2DtoBasic(aux));
        }
        return dtoLista;

    }

    private PersonajeBasicDTO personajeEntity2DtoBasic(PersonajeEntity aux) {

        PersonajeBasicDTO personajeBasicDTO = new PersonajeBasicDTO();

        personajeBasicDTO.setImage(aux.getImage());
        personajeBasicDTO.setName(aux.getName());


        return  personajeBasicDTO;
    }


    public List<PersonajeEntity> perosonajeEntityList(List<PersonajeDTO> peliculaPersonaje) {

        List<PersonajeEntity>personajeEntityLista = new ArrayList<>();

        for(PersonajeDTO aux: peliculaPersonaje){
            personajeEntityLista.add(this.personajeDTO2Entity(aux));
        }

        return personajeEntityLista;



    }
}
