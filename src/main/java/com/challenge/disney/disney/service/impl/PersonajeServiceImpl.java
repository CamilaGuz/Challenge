package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.PersonajeBasicDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;
import com.challenge.disney.disney.dto.PersonajeFiltersDTO;
import com.challenge.disney.disney.entity.PersonajeEntity;
import com.challenge.disney.disney.mapper.PersonajeMapper;
import com.challenge.disney.disney.repository.PersonajeRepository;
import com.challenge.disney.disney.repository.specifications.PersonajeSpecification;
import com.challenge.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServiceImpl implements PersonajeService {


    private PersonajeMapper personajeMapper;

    private PersonajeRepository personajeRepository;

    private PersonajeSpecification personajeSpecification;

    public PersonajeServiceImpl(@Autowired @Lazy PersonajeMapper personajeMapper, PersonajeRepository personajeRepository, PersonajeSpecification personajeSpecification) {
        this.personajeMapper = personajeMapper;
        this.personajeRepository = personajeRepository;
        this.personajeSpecification = personajeSpecification;
    }

    public PersonajeDTO save(PersonajeDTO dto){
        PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity personajeSaved = personajeRepository.save(personajeEntity);
        PersonajeDTO resultado = personajeMapper.personajeEntity2DTO(personajeSaved, false);


        return resultado;
    }



    public List<PersonajeDTO> getAllCharacters(){
        List<PersonajeEntity> entityList = personajeRepository.findAll();
        List<PersonajeDTO> resultado = personajeMapper.personajeEntityList2DTOList(entityList, false);
        return resultado;
    }

    public void delete(Long id){

        personajeRepository.deleteById(id);
    }

    @Override
    public PersonajeDTO putCharacter(Long id, PersonajeDTO edit) {

        PersonajeEntity savedCharacter = this.characterEdit(id);
        savedCharacter.setWeight(edit.getWeight());
        savedCharacter.setName(edit.getName());
        savedCharacter.setImage(edit.getImage());
        savedCharacter.setHistory(edit.getHistory());
        savedCharacter.setAge(edit.getAge());


        PersonajeEntity putCharacter = personajeRepository.save(savedCharacter);
        PersonajeDTO savedDTO = personajeMapper.personajeEntity2DTO(putCharacter, false);
        return savedDTO;
    }
    //basicdto

    @Override
    public List<PersonajeBasicDTO> getAllBasics() {

        List<PersonajeEntity> entityList = personajeRepository.findAll();
        List<PersonajeBasicDTO> resultado = personajeMapper.personajeEntityList2DTOListBasic(entityList);


        return resultado;
    }


    private PersonajeEntity characterEdit(Long id) {
        Optional<PersonajeEntity> personajeEntity = personajeRepository.findById(id);
        if (!personajeEntity.isPresent()){

        }
        return personajeEntity.get();
    }

    @Override
    public PersonajeDTO getDetails(Long id) {

        PersonajeEntity characterFound = personajeRepository.getById(id);
        PersonajeDTO resultado = personajeMapper.personajeEntity2DTO(characterFound, true);
        return resultado;
    }


    public List<PersonajeDTO> getByFilters(String name, Integer age, Set<Long> Idmovie){

        PersonajeFiltersDTO filtersDTO = new PersonajeFiltersDTO(name, age, Idmovie);

        List<PersonajeEntity> entities = this.personajeRepository.findAll(this.personajeSpecification.getByFilters(filtersDTO));
        List<PersonajeDTO> dtos = this.personajeMapper.personajeEntityList2DTOList(entities, true);

        return dtos;
        }




}
