package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByUserAndPassword(String nombre_usuario, String password);
}