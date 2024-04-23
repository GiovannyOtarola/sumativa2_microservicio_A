package com.example.demo.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.demo.model.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void guardarUsuarioTest(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("pepito");
        usuario.setPassword("pepitoHD");

        Usuario resultado = usuarioRepository.save(usuario);

        assertNotNull(resultado.getId());
        assertEquals(1L, resultado.getId());
        assertEquals("pepito", resultado.getNombre());
        assertEquals("pepitoHD", resultado.getPassword());
    }

    
    
}
