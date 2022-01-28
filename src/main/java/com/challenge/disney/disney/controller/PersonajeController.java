package com.challenge.disney.disney.controller;

import com.challenge.disney.disney.dto.PersonajeBasicDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;
import com.challenge.disney.disney.service.PersonajeService;
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


    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO character) {

        PersonajeDTO savedCharacter = personajeService.save(character);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> put(@PathVariable Long id, @RequestBody PersonajeDTO edit) {
        PersonajeDTO putCharacter = personajeService.putCharacter(id, edit);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(putCharacter);
    }


    //Get para obtener una LISTA de imagen y nombre de los personajes

    @GetMapping("/characters")
    public ResponseEntity<List<PersonajeBasicDTO>> getAllBasic() {

        List<PersonajeBasicDTO> basicsCharacters = personajeService.getAllBasics();

        return ResponseEntity.status(HttpStatus.OK).body(basicsCharacters);
    }

    //Get - filtros
    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> getDetailsByFilters (
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String image,
      @RequestParam(required = false) Integer age,
      @RequestParam(required = false) Set<Long> IdMovie)
    {
       List<PersonajeDTO> charactersListDto = this.personajeService.getByFilters(name, image, age, IdMovie);

       return ResponseEntity.status(HttpStatus.OK).body(charactersListDto);
    }







}
