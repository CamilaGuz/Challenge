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

@RestController
@RequestMapping("peliculas")
public class PeliculaController {



    private PeliculaService peliculaService;

    public PeliculaController(@Autowired @Lazy PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO pelicula){

        PeliculaDTO peliculaGuardada = peliculaService.save(pelicula);

    return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }

    @GetMapping
    public  ResponseEntity<List<PeliculaDTO>> getAll(){

        List<PeliculaDTO> peliculas = peliculaService.getAllPelicula();

        return ResponseEntity.status(HttpStatus.OK).body(peliculas);
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        this.peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

     @PutMapping("/{id}")
        public ResponseEntity<PeliculaDTO>put(@PathVariable Long id, @RequestBody PeliculaDTO edit){
            PeliculaDTO putPelicula = peliculaService.putPelicula(id,edit);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(putPelicula);
        }

    @GetMapping("/movies")
    public  ResponseEntity<List<PeliculaBasicDTO>> getAllBasic(){

        List<PeliculaBasicDTO> peliculasBasicas = peliculaService.getAllBasics();

        return ResponseEntity.status(HttpStatus.OK).body(peliculasBasicas);
    }

}
