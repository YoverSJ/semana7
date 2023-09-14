package com.codigo.semana7.infraestructure.controller;

import com.codigo.semana7.application.service.PersonaService;
import com.codigo.semana7.domain.model.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping()
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona){
        Persona createPersona = personaService.crearPersona(persona);
        return new ResponseEntity<>(createPersona, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable("id") Long personaId){
        return personaService.getPersona(personaId).map(persona -> new ResponseEntity<>(persona, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping()
    public ResponseEntity<Persona> updatePersona(@RequestBody Persona persona){
        System.out.println("TEST");
        System.out.println(persona);
        Persona actualizarPersona = personaService.actualizarPersona(persona);
        return new ResponseEntity<>(actualizarPersona, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonaById(@PathVariable("id") Long personaId){
       boolean res = personaService.eliminarPersona(personaId);

       if (res){
           return new ResponseEntity<>("Persona con id " + personaId + " eliminada", HttpStatus.OK);
       }else {
           return new ResponseEntity<>("No se pudo eliminar la Persona con id " + personaId, HttpStatus.BAD_REQUEST);
       }
    }

}
