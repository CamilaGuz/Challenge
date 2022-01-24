package com.challenge.disney.disney.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gender")
@Getter
@Setter
public class GeneroEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String image;

    //private List<PeliculaEntity> genders = new ArrayList<>();



}

