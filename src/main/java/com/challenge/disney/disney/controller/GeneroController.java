package com.challenge.disney.disney.controller;

import com.challenge.disney.disney.dto.GeneroDTO;
import com.challenge.disney.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("genders")
public class GeneroController {


    private GeneroService generoService;

    public GeneroController(@Autowired @Lazy GeneroService generoService) {

        this.generoService = generoService;
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO gender){

        GeneroDTO savedGender = generoService.save(gender);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGender);
    }




}
