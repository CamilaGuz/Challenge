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
@Table (name = "movie")
@Getter
@Setter
@SQLDelete( sql = "UPDATE  movies SET deleted = true WHERE id=?")
@Where( clause = "deleted=false")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateCreation;

    private int qualification;

    private boolean deleted = Boolean.FALSE;



    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )@JoinTable(
            name = "movie_character",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")

    )
    private List<PersonajeEntity> characters = new ArrayList<>();



    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private GeneroEntity gender;






}