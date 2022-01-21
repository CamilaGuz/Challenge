package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.GeneroDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.entity.GeneroEntity;
import com.challenge.disney.disney.entity.PeliculaEntity;
import com.challenge.disney.disney.mapper.GeneroMapper;
import com.challenge.disney.disney.repository.GeneroRepository;
import com.challenge.disney.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService {

    private GeneroMapper generoMapper;

    private GeneroRepository generoRepository;

    public GeneroServiceImpl(@Autowired @Lazy GeneroMapper generoMapper, GeneroRepository generoRepository) {
        this.generoMapper = generoMapper;
        this.generoRepository = generoRepository;
    }

    @Override
    public GeneroDTO save(GeneroDTO genero) {

        GeneroEntity generoEntity = generoMapper.generoDTO2Entity(genero);
        GeneroEntity generoSaved = generoRepository.save(generoEntity);
        GeneroDTO resultado = generoMapper.generoEntity2DTO(generoSaved);

        return resultado;
    }
}
