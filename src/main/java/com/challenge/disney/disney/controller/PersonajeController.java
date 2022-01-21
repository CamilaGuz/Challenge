package com.challenge.disney.disney.controller;

import com.challenge.disney.disney.dto.PersonajeBasicDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;
import com.challenge.disney.disney.service.PersonajeService;
import com.challenge.disney.disney.service.impl.PersonajeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personajes")
public class PersonajeController {


    private PersonajeService personajeService;

    public PersonajeController(@Autowired @Lazy PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping("/characters")
    public  ResponseEntity<List<PersonajeBasicDTO>> getAllBasic(){

        List<PersonajeBasicDTO> personajesBasicos = personajeService.getAllBasics();

        return ResponseEntity.status(HttpStatus.OK).body(personajesBasicos);
    }


    @PostMapping
        public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje){

            PersonajeDTO personajeGuardado = personajeService.save(personaje);


            return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
        }

        @GetMapping
        public  ResponseEntity<List<PersonajeDTO>> getAll(){

            List<PersonajeDTO> personaje = personajeService.getAllPersonajes();

            return ResponseEntity.status(HttpStatus.OK).body(personaje);
        }
    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> detallesDePersonaje(@PathVariable Long id){

        PersonajeDTO personajeDTO = personajeService.getDetalles(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personajeDTO);

    }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void>delete(@PathVariable Long id){
            this.personajeService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        @PutMapping("/{id}")
        public ResponseEntity<PersonajeDTO>put(@PathVariable Long id, @RequestBody PersonajeDTO edit){
            PersonajeDTO putPersonaje = personajeService.putPersonaje(id,edit);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(putPersonaje);
        }





}
