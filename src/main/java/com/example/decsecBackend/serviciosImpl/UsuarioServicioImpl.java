package com.example.decsecBackend.serviciosImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.decsecBackend.dtos.UsuarioDTO;
import com.example.decsecBackend.errores.NotFoundException;
import com.example.decsecBackend.modelo.Usuario;
import com.example.decsecBackend.repositorios.UsuarioRepositorio;
import com.example.decsecBackend.servicios.UsuarioServicio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	@Autowired
	private UsuarioRepositorio repositorio;

	@SuppressWarnings("null")
	@Override
	public Usuario crearUsuario(Usuario usuario) {
		return repositorio.save(usuario);
	}

	@Override
	public Usuario obtenerUsuario(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
	}

	@SuppressWarnings("null")
	@Override
	public void borrarUsuario(Long id) {
		repositorio.deleteById(id);
	}

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				return repositorio.findByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			}
		};
	}

	@Override
	public List<UsuarioDTO> listarTodosUsuariosDTO() {
		return repositorio.findAll().stream()
				.map(usuario -> new UsuarioDTO(usuario))
				.collect(Collectors.toList());
	}

	@Override
	public List<Usuario> listarTodosUsuarios() {
		return repositorio.findAll();
	}

	@Override
	public Boolean existePorEmail(String email) {
		return repositorio.existsByEmail(email);
	}

	@Override
	public Usuario encontrarPorEmail(String email) {
		return repositorio.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
	}

	@SuppressWarnings("null")
	@Override
	public Boolean existePorId(Long id) {
		return repositorio.existsById(id);
	}

	@Override
	public Usuario actualizarUsuario(Long id, Map<String, Object> updates) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Usuario usu = obtenerUsuario(id);

		updates.forEach((campo, valor) -> {
			switch (campo) {
				case "nombre":
					usu.setNombre((String) valor);
					break;
				case "apellidos":
					usu.setApellidos((String) valor);
					break;
				case "fechaNac":
					usu.setFechaNac(LocalDate.parse(valor.toString(), formatter));
					break;
				case "nick":
					usu.setNick((String) valor);
					break;
				case "email":
					usu.setEmail((String) valor);
					break;
				case "password":
					usu.setPassword((String) valor);
					break;
				case "fotoperfil":
					usu.setFotoperfil(valor.toString().getBytes());
					break;
				case "privado":
					usu.setPrivado((valor instanceof Boolean) ? (Boolean) valor : false);
					break;
			}
		});

		return crearUsuario(usu);
	}

	@Override
	public Boolean usuarioPrivado(String email) {
		Usuario usu = repositorio.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

		if (usu.getPrivado()) {
			return true;
		} else {
			return false;
		}
	}

}
