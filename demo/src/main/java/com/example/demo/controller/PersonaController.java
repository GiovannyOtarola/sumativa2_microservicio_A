package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/personas")
public class PersonaController {

    private static final Logger log = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> getAllPersonas(){
        log.info("GET /personas");
        log.info("Retonando todas las personas");
        return personaService.getAllPersonas();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPersonaById(@PathVariable Long id){
        Optional<Persona> persona = personaService.getPersonaById(id);
        
        if (persona.isEmpty()) {
            log.error("No se enonctro para persona con ID {}",id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la persona con ID"+id));
        }
        return ResponseEntity.ok(persona);
    }

    @PostMapping
    public ResponseEntity<Object> createPersona(@Validated @RequestBody Persona persona){
        Persona createdPersona = personaService.createPersona(persona);
        if (createdPersona == null) {
            log.error("Error al crear la persona {}", persona);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error al crear la persona"));
        }
        return ResponseEntity.ok(createdPersona);
    }

    @PutMapping("/{id}")
    public Persona updatePersona(@PathVariable Long id, @RequestBody Persona persona){
        return personaService.updatePersona(id, persona);
    }

    @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable Long id){
        personaService.deletePersona(id);
    }

    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }
}
