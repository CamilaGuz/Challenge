package com.challenge.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PeliculaDTO {

    private Long id;

    private String imagen;

    private String titulo;

    private String fechaCreacion;

    private int calificacion;

    private List<PersonajeDTO> peliculaPersonaje;
}
