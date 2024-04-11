package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Credenciales;

public interface CredencialesRepository  extends JpaRepository<Credenciales, Long> {

    public Credenciales findbyUserAndPasword(String usuario, String contraseña);
}