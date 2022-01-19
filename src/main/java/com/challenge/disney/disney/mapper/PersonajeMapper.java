package com.challenge.disney.disney.mapper;

import com.challenge.disney.disney.dto.PersonajeDTO;
import com.challenge.disney.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;


    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto){

        PersonajeEntity personajeEntity = new PersonajeEntity();

        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setHistoria(dto.getHistoria());
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setPeso(dto.getPeso());

        return personajeEntity;
    }


    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean b) {

        PersonajeDTO dto = new PersonajeDTO();

        dto.setId(entity.getId());
        dto.setEdad(entity.getEdad());
        dto.setHistoria(entity.getHistoria());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setPeso(entity.getPeso());

        if (b){

            dto.setPersonajePelicula(peliculaMapper.peliculaEntityList2DtoList(entity.getPeliculas(), false));

        }


        return dto;
    }

    public List<PersonajeDTO>personajeEntityList2DTOList(List<PersonajeEntity> listaEntity, boolean b) {
        List<PersonajeDTO> dtoList = new ArrayList<>();
        for (PersonajeEntity entity : listaEntity) {
            dtoList.add(this.personajeEntity2DTO(entity, b));

        }
        return dtoList;
    }

   /* public void personajeEntityRefreshValues(PersonajeEntity entity, PersonajeDTO personajeDTO){

        entity.setImagen(personajeDTO.getImagen());
        entity.setNombre(personajeDTO.getNombre());
        entity.setPeso(personajeDTO.getPeso());
        entity.getHistoria(personajeDTO.getHistoria());

    }*/



}
