package com.example.decsecBackend.servicios;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.decsecBackend.dtos.usuarioDTO;
import com.example.decsecBackend.modelo.Usuario;

public interface usuarioServicio {
	List<usuarioDTO> listarTodosUsuariosDTO();
	List<Usuario> listarTodosUsuarios();
	Usuario crearUsuario(Usuario usuario);
	Usuario obtenerUsuario(Long id);
	void borrarUsuario(Long id);
    UserDetailsService userDetailsService();
    Boolean existePorEmail(String email);
    Boolean existePorId(Long id);
    Usuario encontrarPorEmail(String email);
}
