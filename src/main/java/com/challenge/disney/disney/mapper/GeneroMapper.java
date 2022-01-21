package com.challenge.disney.disney.mapper;

import com.challenge.disney.disney.dto.GeneroDTO;
import com.challenge.disney.disney.entity.GeneroEntity;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {


    public GeneroEntity generoDTO2Entity(GeneroDTO genero) {

        GeneroEntity generoEntity = new GeneroEntity();

        generoEntity.setImagen(genero.getImagen());
        generoEntity.setNombre(genero.getNombre());


        return generoEntity;
    }

    public GeneroDTO generoEntity2DTO(GeneroEntity entity) {

        GeneroDTO generoDTO = new GeneroDTO();

        generoDTO.setImagen(entity.getImagen());
        generoDTO.setNombre(entity.getNombre());
        generoDTO.setId(entity.getId());

        return generoDTO;
    }
}
