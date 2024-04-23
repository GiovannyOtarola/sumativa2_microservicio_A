package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @Test
    public void guardarUsuarioTest(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Pedro");
        usuario.setPassword("PedroHD");

        when(usuarioRepositoryMock.save(any())).thenReturn(usuario);

        Usuario resultado = usuarioService.createUsuario(usuario);

        assertEquals(1L, resultado.getId());
        assertEquals("Pedro", resultado.getNombre());
        assertEquals("PedroHD", resultado.getPassword());
    }
    
}
