package com.challenge.disney.disney.controller;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.dto.PersonajeBasicDTO;
import com.challenge.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class PeliculaController {


    private PeliculaService peliculaService;

    public PeliculaController(@Autowired @Lazy PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    //Post para pelicula

    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO movie){

        PeliculaDTO savedMovie= peliculaService.save(movie);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    //Get para todas las peliculas

    @GetMapping("/All")
    public  ResponseEntity<List<PeliculaDTO>> getAll(){

        List<PeliculaDTO> movies = peliculaService.getAllMovies();

        return ResponseEntity.status(HttpStatus.OK).body(movies);
        }

    // Get de atributos basicos de pelicula

    @GetMapping("/movies")
    public  ResponseEntity<List<PeliculaBasicDTO>> getAllBasic(){

        List<PeliculaBasicDTO> basicMovies = peliculaService.getAllBasics();

        return ResponseEntity.status(HttpStatus.OK).body(basicMovies);
    }

    //Get para busqueda con filtros

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Set<Long> genres,
            @RequestParam(required = false, defaultValue = "ASC") String order)

    { List<PeliculaDTO> movies = this.peliculaService.getByFilters(title, genres,order);

        return ResponseEntity.ok(movies);

    }



    // Borrar pelicula

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        this.peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    // Put pelicula

     @PutMapping("/{id}")
        public ResponseEntity<PeliculaDTO>put(@PathVariable Long id, @RequestBody PeliculaDTO edit){
            PeliculaDTO putMovie = peliculaService.putMovie(id,edit);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(putMovie);
        }



}
