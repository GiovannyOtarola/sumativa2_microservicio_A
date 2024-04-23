package com.example.demo.controller;

import static org.mockito.Mockito.when;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioServiceImpl;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioServiceImpl usuarioServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception{
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("usuario1");
        usuario1.setPassword("1234");
        usuario1.setId(1L);

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("usuario2");
        usuario2.setPassword("123456");
        usuario2.setId(2L);

        List<Usuario> usuarios = Arrays.asList(usuario1,usuario2);

        when(usuarioServiceMock.getAllUsuarios()).thenReturn(usuarios);

        mockMvc.perform(MockMvcRequestBuilders.get("/Usuarios"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[0].nombre", Matchers.is("usuario1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[0].password", Matchers.is("1234")))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[0]._links.self.href", Matchers.is("http://localhost/Usuarios/1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[1].nombre", Matchers.is("usuario2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[1].password", Matchers.is("123456")))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[1]._links.self.href", Matchers.is("http://localhost/Usuarios/2")));

    }

    @Test
    public void obtenerUsuarioPorIdTest() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("usuario");
        usuario.setPassword("123a");
        usuario.setId(1L);
    
        when(usuarioServiceMock.getUsuarioById(1L)).thenReturn(Optional.of(usuario));
    
        mockMvc.perform(MockMvcRequestBuilders.get("/Usuarios/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", Matchers.is("usuario")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("123a")));
    }


   
}
