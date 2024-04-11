package com.example.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @Column(name = "id")
    private Long id;

    @NotNull(message = "user obligatorio")
    @NotBlank(message = "No puede ingresar un user vacio")
    @Column(name= "user")
    private String user;

    @NotNull(message = "password obligatoria")
    @NotBlank(message = "No puede ingresar una password vacia")
    @Column(name= "password")
    private String password;

    
}
