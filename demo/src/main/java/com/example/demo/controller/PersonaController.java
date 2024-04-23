package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

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

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/personas")
public class PersonaController {

    private static final Logger log = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public CollectionModel<EntityModel<Persona>> getAllPersonas() {
        List<Persona> personas = personaService.getAllPersonas();
        log.info("GET /personas");
        log.info("Retornando todas las personas");
        List<EntityModel<Persona>> studentsResources = personas.stream()
            .map( persona -> EntityModel.of(persona,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPersonaById(persona.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPersonas());
        CollectionModel<EntityModel<Persona>> resources = CollectionModel.of(studentsResources, linkTo.withRel("personas"));

        return resources;
    }

    @GetMapping("/{id}")
    public EntityModel<Persona> getPersonaById(@PathVariable Long id) {
        Optional<Persona> persona = personaService.getPersonaById(id);

        if (persona.isPresent()) {
            return EntityModel.of(persona.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPersonaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPersonas()).withRel("all-personas"));
        } else {
            throw new PersonaNotFoundException("Persona not found with id: " + id);
        }
    }

    @PostMapping
    public EntityModel<Persona> createPersona(@Validated @RequestBody Persona persona) {
        Persona createdPersona = personaService.createPersona(persona);
            return EntityModel.of(createdPersona,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPersonaById(createdPersona.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPersonas()).withRel("all-personas"));

    }

    @PutMapping("/{id}")
    public EntityModel<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona persona) {
        Persona updatedPersona = personaService.updatePersona(id, persona);
        return EntityModel.of(updatedPersona,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPersonaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPersonas()).withRel("all-personas"));

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
