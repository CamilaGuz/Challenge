package com.challenge.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
public class PersonajeFiltersDTO {

    private String name;
    private Integer age;
    private Set<Long> Idmovie;

    public PersonajeFiltersDTO(String name, Integer age, Set<Long> idmovie) {
        this.name = name;
        this.age = age;
        Idmovie = idmovie;
    }



}
