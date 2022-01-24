package com.challenge.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PeliculaFiltersDTO {

    private String title;
    private Set<Long> genres;
    private String order;


    public PeliculaFiltersDTO(String name, Set<Long> genres, String order) {
        this.title = name;
        this.genres = genres;
        this.order = order;
       
    }


    public boolean isASC() { return this.order.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC() { return this.order.compareToIgnoreCase("DESC") == 0;}
}
