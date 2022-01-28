package com.challenge.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PeliculaFiltersDTO {

    private String title;
    private String image;
    private String dateCreation;
    private Set<Long> gender;
    private String order;

    public PeliculaFiltersDTO(String title, String image, String dateCreation, Set<Long> gender, String order) {
        this.title = title;
        this.image = image;
        this.dateCreation = dateCreation;
        this.gender = gender;
        this.order = order;
    }

    public boolean isASC() { return this.order.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC() { return this.order.compareToIgnoreCase("DESC") == 0;}
}
