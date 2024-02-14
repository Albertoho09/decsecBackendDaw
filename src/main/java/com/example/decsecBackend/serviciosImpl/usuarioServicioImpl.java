package com.example.decsecBackend.serviciosImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.decsecBackend.dtos.usuarioDTO;
import com.example.decsecBackend.modelo.Usuario;
import com.example.decsecBackend.repositorios.usuarioRepositorio;
import com.example.decsecBackend.servicios.usuarioServicio;

@Service
public class usuarioServicioImpl implements usuarioServicio{

	@Autowired
	private usuarioRepositorio repositorio;
	
	@Override
	public Usuario crearUsuario(Usuario usuario) {
		return repositorio.save(usuario);
	}

	@Override
	public Usuario obtenerUsuario(Long id) {
		return repositorio.findById(id).orElse(null);
	}

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
	public List<usuarioDTO> listarTodosUsuariosDTO() {
	    return  repositorio.findAll().stream()
                .map(usuario -> new usuarioDTO(usuario))
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
		return repositorio.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
	}

	@Override
	public Boolean existePorId(Long id) {
		return repositorio.existsById(id);
	}

}
