package com.challenge.disney.disney.mapper;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.dto.PersonajeBasicDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;
import com.challenge.disney.disney.entity.GeneroEntity;
import com.challenge.disney.disney.entity.PeliculaEntity;
import com.challenge.disney.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaMapper {

    private PersonajeMapper personajeMapper;

    public PeliculaMapper(@Autowired @Lazy PersonajeMapper personajeMapper) {
        this.personajeMapper = personajeMapper;
    }




    // dto a entity

    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto, GeneroEntity generoEntity) {

        PeliculaEntity peliculaEntity = new PeliculaEntity();


        peliculaEntity.setTitle(dto.getTitle());
        peliculaEntity.setDateCreation(this.String2LocalDate(dto.getDateCreation()));
        peliculaEntity.setImage(dto.getImage());
        peliculaEntity.setQualification(dto.getQualification());
        peliculaEntity.setCharacters(personajeMapper.perosonajeEntityList(dto.getMovieCharacters()));
        peliculaEntity.setGender(generoEntity);


        return peliculaEntity;
    }

    //Entity a dto

    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity, boolean showCharacters){

        PeliculaDTO dto = new PeliculaDTO();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDateCreation(entity.getDateCreation().toString());
        dto.setImage(entity.getImage());
        dto.setQualification(entity.getQualification());
        dto.setMovieCharacters(personajeMapper.personajeEntityList2DTOList(entity.getCharacters(), false));
        dto.setGenderId(entity.getGender().getId());

        return dto;
    }

    //guardar en lista de dto

    public List<PeliculaDTO> peliculaEntityList2DtoList(List<PeliculaEntity> listaEntity, boolean b){
        List<PeliculaDTO>dtoLista = new ArrayList<>();
        for(PeliculaEntity ent : listaEntity){
            dtoLista.add(this.peliculaEntity2DTO(ent, b));
        }
        return dtoLista;
    }


    //parsear la fecha

    public LocalDate String2LocalDate(String stringDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, format);
        return date;
    }

    // Entity a dto de peliculas basicas

    public List<PeliculaBasicDTO> peliculaBasicEntityList2DtoList(List<PeliculaEntity> peliculaEntities) {

        List<PeliculaBasicDTO>dtoLista = new ArrayList<>();
        for(PeliculaEntity aux : peliculaEntities){
            dtoLista.add(this.peliculaBasicEntity2Dto(aux));
        }
        return dtoLista;


    }

    private PeliculaBasicDTO peliculaBasicEntity2Dto(PeliculaEntity aux) {

        PeliculaBasicDTO peliculaBasicDTO = new PeliculaBasicDTO();

        peliculaBasicDTO.setImage(aux.getImage());
        peliculaBasicDTO.setTitle(aux.getTitle());
        peliculaBasicDTO.setDateCreation(aux.getDateCreation().toString());

        return peliculaBasicDTO;
    }

}


