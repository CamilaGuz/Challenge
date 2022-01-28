package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.dto.PeliculaFiltersDTO;
import com.challenge.disney.disney.entity.GeneroEntity;
import com.challenge.disney.disney.entity.PeliculaEntity;
import com.challenge.disney.disney.mapper.PeliculaMapper;
import com.challenge.disney.disney.repository.GeneroRepository;
import com.challenge.disney.disney.repository.PeliculaRepository;
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


    public PeliculaServiceImpl(@Autowired @Lazy PeliculaMapper peliculaMapper, @Autowired @Lazy PeliculaRepository peliculaRepository, @Autowired @Lazy GeneroRepository generoRepository, @Autowired @Lazy PeliculaSpecification peliculaSpecification) {
        this.peliculaMapper = peliculaMapper;
        this.peliculaRepository = peliculaRepository;
        this.generoRepository = generoRepository;
        this.peliculaSpecification = peliculaSpecification;
    }

    //Post
    @Override
    public PeliculaDTO save(PeliculaDTO movie) {

        GeneroEntity genderDetected =  generoRepository.getById(movie.getGenderId());

        PeliculaEntity peliculaEntity = peliculaMapper.peliculaDTO2Entity(movie, genderDetected);
        PeliculaEntity savedMovie = peliculaRepository.save(peliculaEntity);
        PeliculaDTO resultado = peliculaMapper.peliculaEntity2DTO(savedMovie, false);

        return resultado;
    }

    //delete
    @Override
    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }
    //Put
    @Override
    public PeliculaDTO putMovie(Long id, PeliculaDTO edit) {

        PeliculaEntity savedMovie = this.getpeliculaById(id);

        savedMovie.setImage(edit.getImage());
        savedMovie.setTitle(edit.getTitle());
        savedMovie.setDateCreation(peliculaMapper.String2LocalDate(edit.getDateCreation()));
        savedMovie.setQualification(edit.getQualification());

        PeliculaEntity editMovie = peliculaRepository.save(savedMovie);
        PeliculaDTO  saveDTO = peliculaMapper.peliculaEntity2DTO(editMovie, false);

        return saveDTO;
    }


    private PeliculaEntity getpeliculaById(Long id) {
        Optional<PeliculaEntity> peliculaEntity = peliculaRepository.findById(id);
        if (!peliculaEntity.isPresent()){
        }
        return peliculaEntity.get();
    }

    //filtros

    public List<PeliculaDTO> getByFilters(String title, String image, String dateCreation, Set<Long> gender, String order){

        PeliculaFiltersDTO filtersDTO = new PeliculaFiltersDTO(title, image, dateCreation, gender, order);

        List<PeliculaEntity> entities = peliculaRepository.findAll(peliculaSpecification.getByFilters(filtersDTO));
        List<PeliculaDTO> resultado = peliculaMapper.peliculaEntityList2DtoList(entities, true);

        return resultado;
    }

    @Override
    public List<PeliculaBasicDTO> getAllBasics() {

        List<PeliculaEntity> peliculaEntities= peliculaRepository.findAll();
        List<PeliculaBasicDTO> resultado =  peliculaMapper.peliculaBasicEntityList2DtoList(peliculaEntities);

        return resultado;

    }


}
