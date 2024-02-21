package com.example.decsecBackend.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.decsecBackend.modelo.Role;
import com.example.decsecBackend.modelo.Usuario;
import com.example.decsecBackend.serviciosImpl.publicacionServicioImpl;

import lombok.Data;

@Data
public class usuarioDTO {

	@Autowired
	private publicacionServicioImpl servicioPublicacion;

	private String nick;

	private String nombre;

	private String apellidos;

	private String email;

	private LocalDate fechaNac;

	private int npublicaciones;

	private Set<Role> roles = new HashSet<>();

	public usuarioDTO(Usuario usu) {
		this.nick = usu.getNick();
		this.nombre = usu.getNick();
		this.apellidos = usu.getApellidos();
		this.email = usu.getEmail();
		this.fechaNac = usu.getFechaNac();
		this.roles = usu.getRoles();
		this.npublicaciones = servicioPublicacion.listarPublicacionesUsuario(usu.getEmail()).size();
	}

}
