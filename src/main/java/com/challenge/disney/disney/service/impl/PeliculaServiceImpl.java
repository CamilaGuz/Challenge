package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.entity.GeneroEntity;
import com.challenge.disney.disney.entity.PeliculaEntity;
import com.challenge.disney.disney.mapper.PeliculaMapper;
import com.challenge.disney.disney.repository.GeneroRepository;
import com.challenge.disney.disney.repository.PeliculaRepository;
import com.challenge.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {


    private PeliculaMapper peliculaMapper;

    private PeliculaRepository peliculaRepository;

    private GeneroRepository generoRepository;

    public PeliculaServiceImpl(@Autowired @Lazy PeliculaMapper peliculaMapper, PeliculaRepository peliculaRepository, GeneroRepository generoRepository) {
        this.peliculaMapper = peliculaMapper;
        this.peliculaRepository = peliculaRepository;
        this.generoRepository = generoRepository;
    }

    @Override
    public PeliculaDTO save(PeliculaDTO pelicula) {

        GeneroEntity generoDetectado =  generoRepository.getById(pelicula.getGeneroId());

        PeliculaEntity peliculaEntity = peliculaMapper.peliculaDTO2Entity(pelicula, generoDetectado);
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
        peliculaRepository.deleteById(id);
    }

    @Override
    public PeliculaDTO putPelicula(Long id, PeliculaDTO edit) {

        PeliculaEntity savedPelicula = this.peliculaEdit(id);

        savedPelicula.setImagen(edit.getImagen());
        savedPelicula.setTitulo(edit.getTitulo());
        savedPelicula.setFechaCreacion(peliculaMapper.String2LocalDate(edit.getFechaCreacion()));
        savedPelicula.setCalificacion(edit.getCalificacion());

        PeliculaEntity editarPelicula = peliculaRepository.save(savedPelicula);
        PeliculaDTO  guardarDTO = peliculaMapper.peliculaEntity2DTO(editarPelicula, false);


        return guardarDTO;
    }
    //BasciPeliculas
    @Override
    public List<PeliculaBasicDTO> getAllBasics() {

       List<PeliculaEntity> peliculaEntities= peliculaRepository.findAll();
       List<PeliculaBasicDTO> resultado =  peliculaMapper.peliculaBasicEntityList2DtoList(peliculaEntities);

       return resultado;
    }

    private PeliculaEntity peliculaEdit(Long id) {

        Optional<PeliculaEntity> peliculaEntity = peliculaRepository.findById(id);
        if (!peliculaEntity.isPresent()){

        }
        return peliculaEntity.get();
    }


}
