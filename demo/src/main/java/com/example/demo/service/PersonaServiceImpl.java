package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> getAllPersonas(){
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> getPersonaById(Long id){
        return personaRepository.findById(id);
    }

    @Override
    public Persona createPersona(Persona persona){
        return personaRepository.save(persona);
    }

    @Override
    public Persona updatePersona(Long id, Persona persona){
        if (personaRepository.existsById(id)) {
            persona.setId(id);
            return personaRepository.save(persona);
        } else {
            return null;
        }
    }

    @Override
    public void deletePersona(Long id){
        personaRepository.deleteById(id);
    }
}
