package com.challenge.disney.disney.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "pelicula")
@Getter
@Setter
@SQLDelete( sql = "UPDATE  personaje SET deleted = true WHERE id=?")
@Where( clause = "deleted=false")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String titulo;

    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;

    private int calificacion;

    private boolean deleted = Boolean.FALSE;



    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )@JoinTable(
            name = "pelicula_personaje",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id")

    )
    private List<PersonajeEntity> personajes = new ArrayList<>();



    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name = "genero_id", insertable = false, updatable = false)
    private GeneroEntity genero;

   // @Column(name = "genero_id", nullable = false)
   //  private Long generoId;



}