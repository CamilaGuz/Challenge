package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;
import com.challenge.disney.disney.entity.PeliculaEntity;
import com.challenge.disney.disney.entity.PersonajeEntity;
import com.challenge.disney.disney.mapper.PeliculaMapper;
import com.challenge.disney.disney.repository.PeliculaRepository;
import com.challenge.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PeliculaRepository peliculaRepository;


    @Override
    public PeliculaDTO save(PeliculaDTO pelicula) {

        PeliculaEntity peliculaEntity = peliculaMapper.peliculaDTO2Entity(pelicula);
        PeliculaEntity peliculaSaved = peliculaRepository.save(peliculaEntity);
        PeliculaDTO resultado = peliculaMapper.peliculaEntity2DTO(peliculaSaved, false);

        return resultado;
    }

    public List<PeliculaDTO> getAllPelicula(){
        List<PeliculaEntity> entityList = peliculaRepository.findAll();
        List<PeliculaDTO> resultado = peliculaMapper.peliculaEntityList2DtoList(entityList, false);
        return resultado;
    }

    @Override
    public void delete(Long id) {
        peliculaRepository.deleteById();
    }


}
