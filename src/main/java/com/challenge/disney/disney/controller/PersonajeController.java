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
import java.util.Set;

@RestController
@RequestMapping("characters")
public class PersonajeController {


    private PersonajeService personajeService;

    public PersonajeController(@Autowired @Lazy PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    //Get para personajes basicos


    @GetMapping("/characters")
    public ResponseEntity<List<PersonajeBasicDTO>> getAllBasic() {

        List<PersonajeBasicDTO> basicsCharacters = personajeService.getAllBasics();

        return ResponseEntity.status(HttpStatus.OK).body(basicsCharacters);
    }

    //Get para personajes con filtro

    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> getDetailsByFilters (
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Integer age,
      @RequestParam(required = false) Set<Long> IdMovie)
    {
       List<PersonajeDTO> characters = this.personajeService.getByFilters(name, age, IdMovie);

       return ResponseEntity.ok(characters);
    }

    //Get para todos los personajes

    @GetMapping("/all")
    public ResponseEntity<List<PersonajeDTO>> getAll() {

        List<PersonajeDTO> characters = personajeService.getAllCharacters();

        return ResponseEntity.status(HttpStatus.OK).body(characters);
    }

    //Get para personajes por id

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> charactersDetails(@PathVariable Long id) {

        PersonajeDTO personajeDTO = personajeService.getDetails(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personajeDTO);

    }


    //Post para pesonajes

    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO character) {

        PersonajeDTO savedCharacter = personajeService.save(character);


        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }


    //delete por id para personajes

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Put por id para pesonajes

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> put(@PathVariable Long id, @RequestBody PersonajeDTO edit) {
        PersonajeDTO putCharacter = personajeService.putCharacter(id, edit);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(putCharacter);
    }





}
