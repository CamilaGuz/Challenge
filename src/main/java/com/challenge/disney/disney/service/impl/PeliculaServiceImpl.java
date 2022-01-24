package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.dto.PeliculaFiltersDTO;
import com.challenge.disney.disney.entity.GeneroEntity;
import com.challenge.disney.disney.entity.PeliculaEntity;
import com.challenge.disney.disney.mapper.PeliculaMapper;
import com.challenge.disney.disney.repository.GeneroRepository;
import com.challenge.disney.disney.repository.PeliculaRepository;
import com.challenge.disney.disney.repository.specifications.PersonajeSpecification;
import com.challenge.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.challenge.disney.disney.repository.specifications.PeliculaSpecification;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PeliculaServiceImpl implements PeliculaService {


    private PeliculaMapper peliculaMapper;

    private PeliculaRepository peliculaRepository;

    private GeneroRepository generoRepository;

    private PeliculaSpecification peliculaSpecification;


    public PeliculaServiceImpl(@Autowired @Lazy PeliculaMapper peliculaMapper, PeliculaRepository peliculaRepository, GeneroRepository generoRepository, PeliculaSpecification peliculaSpecification) {
        this.peliculaMapper = peliculaMapper;
        this.peliculaRepository = peliculaRepository;
        this.generoRepository = generoRepository;
        this.peliculaSpecification = peliculaSpecification;
    }

    @Override
    public PeliculaDTO save(PeliculaDTO pelicula) {

        GeneroEntity genderDetected =  generoRepository.getById(pelicula.getGenderId());

        PeliculaEntity peliculaEntity = peliculaMapper.peliculaDTO2Entity(pelicula, genderDetected);
        PeliculaEntity savedMovie = peliculaRepository.save(peliculaEntity);
        PeliculaDTO resultado = peliculaMapper.peliculaEntity2DTO(savedMovie, false);

        return resultado;
    }

    public List<PeliculaDTO> getAllMovies(){
        List<PeliculaEntity> entityList = peliculaRepository.findAll();
        List<PeliculaDTO> resultado = peliculaMapper.peliculaEntityList2DtoList(entityList, false);
        return resultado;
    }

    @Override
    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }

    @Override
    public PeliculaDTO putMovie(Long id, PeliculaDTO edit) {

        PeliculaEntity savedPelicula = this.peliculaEdit(id);

        savedPelicula.setImage(edit.getImage());
        savedPelicula.setTitle(edit.getTitle());
        savedPelicula.setDateCreation(peliculaMapper.String2LocalDate(edit.getDateCreation()));
        savedPelicula.setQualification(edit.getQualification());

        PeliculaEntity editMovie = peliculaRepository.save(savedPelicula);
        PeliculaDTO  saveDTO = peliculaMapper.peliculaEntity2DTO(editMovie, false);


        return saveDTO;
    }

    // trabajamos con la lista de BasciPeliculas

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

    // Peliculas por filtros

    public List<PeliculaDTO> getByFilters(String title, Set<Long> genres, String order){

        PeliculaFiltersDTO filtersDTO = new PeliculaFiltersDTO(title, genres, order);

        List<PeliculaEntity> entities = this.peliculaRepository.findAll(this.peliculaSpecification.getByFilters(filtersDTO));peliculaSpecification.getByFilters(filtersDTO);
        List<PeliculaDTO> dtos = this.peliculaMapper.peliculaEntityList2DtoList(entities, true);

        return dtos;
    }


}
