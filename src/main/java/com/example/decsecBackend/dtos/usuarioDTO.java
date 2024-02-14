package com.example.decsecBackend.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.decsecBackend.modelo.Role;
import com.example.decsecBackend.modelo.Usuario;

public class usuarioDTO {
	
	private String nick;
	
	private String nombre;
	
	private String apellidos;
	
    private String email;

	private Date fechaNac;
	
	private int npublicaciones;
	
    private Set<Role> roles = new HashSet<>();

    
	public usuarioDTO(Usuario usu) {
		this.nick = usu.getNick();
		this.nombre = usu.getNick();
		this.apellidos = usu.getApellidos();
		this.email = usu.getEmail();
		this.fechaNac = usu.getFechaNac();
		this.roles = usu.getRoles();
		this.npublicaciones = 0;
	}

	
	public int getNpublicaciones() {
		return npublicaciones;
	}


	public void setNpublicaciones(int npublicaciones) {
		this.npublicaciones = npublicaciones;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

}
