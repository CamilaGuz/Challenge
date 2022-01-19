package com.challenge.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonajeDTO {
    private Long id;
    private String imagen;
    private String nombre;
    private int edad;
    private double peso;
    private String historia;
    private List<PeliculaDTO> personajePelicula;



}
