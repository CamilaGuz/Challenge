package com.challenge.disney.disney.mapper;

import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.entity.PeliculaEntity;
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

    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto) {

        PeliculaEntity peliculaEntity = new PeliculaEntity();


        peliculaEntity.setTitulo(dto.getTitulo());
        peliculaEntity.setFechaCreacion(this.String2LocalDate(dto.getFechaCreacion()));
        peliculaEntity.setImagen(dto.getImagen());
        peliculaEntity.setCalificacion(dto.getCalificacion());


        return peliculaEntity;
    }


    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity, boolean mostrarPersonajes){

        PeliculaDTO dto = new PeliculaDTO();

        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setFechaCreacion(entity.getFechaCreacion().toString());
        dto.setImagen(entity.getImagen());
        dto.setCalificacion(entity.getCalificacion());

        if (mostrarPersonajes){
             dto.setPeliculaPersonaje(personajeMapper.personajeEntityList2DTOList(entity.getPersonajes(),false));
        }

        return dto;
    }
    public List<PeliculaDTO> peliculaEntityList2DtoList(List<PeliculaEntity> listaEntity, boolean b){
        List<PeliculaDTO>dtoLista = new ArrayList<>();
        for(PeliculaEntity ent : listaEntity){
            dtoLista.add(this.peliculaEntity2DTO(ent, b));
        }
        return dtoLista;
    }



    public LocalDate String2LocalDate(String stringDate) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formato);
        return date;
    }


}


