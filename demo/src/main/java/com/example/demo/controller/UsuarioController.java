package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;



import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {
    private static final Logger log = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private UsuarioService usuarioService;

   

    @GetMapping
    public List<Usuario> getAllPersonas(){
        log.info("GET /usuarios");
        log.info("Retonando todos los usuarios");
        return usuarioService.getAllUsuarios();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPersonaById(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        
        if (usuario.isEmpty()) {
            log.error("No se encontro para usuario con ID {}",id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró al usuario con ID"+id));
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Object> createUsuario(@Validated @RequestBody Usuario usuario){
        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        if (createdUsuario == null) {
            log.error("Error al crear al usuario {}", usuario);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error al crear al usuario"));
        }
        return ResponseEntity.ok(createdUsuario);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@Validated @RequestBody Usuario usuario) {
        Usuario usuarioLogueado = usuarioService.loginUsuario(usuario.getNombre(), usuario.getPassword());
        if (usuarioLogueado != null) {
            
            return ResponseEntity.ok(usuarioLogueado);
        } else {
            log.error("Error de logeo");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Usuario o Contraseña incorrecta"));
        }
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioService.updateUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
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
