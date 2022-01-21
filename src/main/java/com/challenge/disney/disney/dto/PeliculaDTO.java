package com.challenge.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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

    @Column(name = "genero_id")
    private Long generoId;
}
