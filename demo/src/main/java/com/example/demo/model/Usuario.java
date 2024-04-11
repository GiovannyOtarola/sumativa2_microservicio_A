package com.example.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "credenciales")
public class Usuario {

    @NotNull(message = "usuario obligatorio")
    @NotBlank(message = "No puede ingresar un usuario vacio")
    @Column(name= "user")
    private String user;

    @NotNull(message = "contraseña obligatoria")
    @NotBlank(message = "No puede ingresar una contraseña vacia")
    @Column(name= "password")
    private String password;

    @NotNull(message = "ID obligatorio")
    @NotBlank(message = "No puede ingresar un ID vacio")
    @Column(name = "id_persona")
    private Long id_persona;
}
