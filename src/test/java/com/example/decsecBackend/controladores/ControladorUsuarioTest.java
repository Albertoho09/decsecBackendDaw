package com.example.decsecBackend.controladores;

import com.example.decsecBackend.modelo.Role;
import com.example.decsecBackend.modelo.Usuario;
import com.example.decsecBackend.serviciosImpl.UsuarioServicioImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class ControladorUsuarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioServicioImpl usuarioServicio;

    @Test
    void testListarUsuarios() throws Exception {
        // Simular autenticación y obtener token
        mockMvc.perform(post("/login")
                .param("email", "ana@gmail.com")
                .param("password", "tomate"))
                .andExpect(status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());

        // Realizar solicitudes adicionales con el token obtenido
        mockMvc.perform(get("/api/v1/users"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testListarUsuariosPorId() throws Exception {
        Long usuarioId = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setRoles(Collections.singleton(Role.ROLE_USER));

        when(usuarioServicio.existePorId(usuarioId)).thenReturn(true);
        when(usuarioServicio.obtenerUsuario(usuarioId)).thenReturn(usuario);

        mockMvc.perform(get("/api/v1/users/{id}", usuarioId))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id").value(usuarioId))
               .andExpect(jsonPath("$.roles[0]").value("ROLE_USER"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testBorrarUsuarioPorId() throws Exception {
        Long usuarioId = 1L;

        when(usuarioServicio.existePorId(usuarioId)).thenReturn(true);
        usuarioServicio.borrarUsuario(usuarioId);
        when(usuarioServicio.existePorId(usuarioId)).thenReturn(true);
        
        mockMvc.perform(delete("/api/v1/users/{id}", usuarioId))
               .andExpect(status().isOk())
               .andExpect(content().string("Usuario con id:" + usuarioId + " borrado correctamente"));
    }
}
