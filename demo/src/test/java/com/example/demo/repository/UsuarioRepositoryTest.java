package com.example.demo.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.assertj.core.api.Assertions;
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

    @Test
    public void obtenerUsuarioPorIdTest(){

       //Usuario 1 guardado en la base de datos
       //nombre: usuario1
       //password: password1

        Usuario resultado = usuarioRepository.findById(1L).get();

        assertNotNull(resultado.getId());
        assertEquals(1L, resultado.getId());
        assertEquals("usuario1", resultado.getNombre());
        assertEquals("password1", resultado.getPassword());

    }
    //verificar si el metodo findById funciona correctamente
    @Test
    public void buscarUsuarioPorIdTest(){
        Usuario usuario = usuarioRepository.findById(1L).get();

        Assertions.assertThat(usuario.getId()).isEqualTo(1L);
    }

    //verifica si el metodo findAll funciona correctamente y que la base de datos no esta vacia
    @Test
    public void findAllUsuariosTest(){

        List<Usuario> usuarios =  usuarioRepository.findAll();

        Assertions.assertThat(usuarios.size()).isGreaterThan(0);
    }
    
    
}
