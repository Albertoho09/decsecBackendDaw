package com.example.decsecBackend.controladores;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.decsecBackend.dtos.UsuarioDTO;
import com.example.decsecBackend.modelo.Role;
import com.example.decsecBackend.modelo.Usuario;
import com.example.decsecBackend.serviciosImpl.UsuarioServicioImpl;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(userController.class);

	@Autowired
	private UsuarioServicioImpl usuarioservice;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> listarUsuarios(
			@AuthenticationPrincipal Usuario usuario) {
		if (usuario.getRoles().contains(Role.ROLE_USER)) {
			return ResponseEntity.ok(usuarioservice.listarTodosUsuariosDTO());
		} else {
			return ResponseEntity.ok(usuarioservice.listarTodosUsuarios());
		}
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> listarUsuariosPorId(@PathVariable Long id,
			@AuthenticationPrincipal Usuario usuario) {
		if (usuario.getRoles().contains(Role.ROLE_ADMIN)) {
			if (usuarioservice.existePorId(id)) {
				return ResponseEntity.ok(usuarioservice.obtenerUsuario(id));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con id:"+id+" no existe");
		} else {
			if (usuarioservice.existePorId(id)) {
				return ResponseEntity.ok(new UsuarioDTO(usuarioservice.obtenerUsuario(id)));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con id:"+id+" no existe");
		}
	}

	@DeleteMapping
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> borrarMiUsuario(
			@AuthenticationPrincipal Usuario usuario) {
		usuarioservice.borrarUsuario(usuario.getId());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> borrarUsuarioPorId(@PathVariable Long id,
			@AuthenticationPrincipal Usuario usuario) {
		if (usuarioservice.existePorId(id)) {
			usuarioservice.borrarUsuario(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> actualizarParcialmenteOtroUsuario(@PathVariable(required = false) Long id,
			@AuthenticationPrincipal Usuario usuario, @RequestBody Map<String, Object> updates) {
		if (usuarioservice.existePorId(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(usuarioservice.actualizarUsuario(usuario.getId(),
					updates));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PatchMapping
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> actualizarParcialmenteMiUsuario(
			@AuthenticationPrincipal Usuario usuario, @RequestBody Map<String, Object> updates) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioservice.actualizarUsuario(usuario.getId(),
				updates));
	}

}
