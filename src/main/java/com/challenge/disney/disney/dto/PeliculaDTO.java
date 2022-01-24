package com.challenge.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
public class PeliculaDTO {

    private Long id;

    private String image;

    private String title;

    private String dateCreation;

    private int qualification;

    private List<PersonajeDTO> movieCharacters;

    @Column(name = "gender_id")
    private Long genderId;
}
