package com.example.demo.service;
import com.example.demo.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> getAllUsuarios();
    Optional<Usuario> getUsuarioById(Long id);
    Usuario createUsuario(Usuario persona);
    Usuario updateUsuario(Long id,Usuario usuario);
    void deleteUsuario(Long id);
    Usuario login(String user, String password);
}