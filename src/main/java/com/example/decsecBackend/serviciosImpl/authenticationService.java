package com.example.decsecBackend.serviciosImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.decsecBackend.dtos.SigninRequest;
import com.example.decsecBackend.modelo.Role;
import com.example.decsecBackend.modelo.Usuario;
import com.example.decsecBackend.dtos.SignUpRequest;
import com.example.decsecBackend.seguridad.JwtAuthenticationResponse;

import com.example.decsecBackend.serviciosImpl.JwtServiceImpl;

import lombok.Builder;

@Builder
@Service
public class authenticationService {
	@Autowired
	private usuarioServicioImpl usuarioservicio;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    // Constructor para inyección de dependencias (si usas Spring)
    public authenticationService(usuarioServicioImpl servicio,
                                     PasswordEncoder passwordEncoder,
                                     JwtServiceImpl jwtService,
                                     AuthenticationManager authenticationManager) {
        this.usuarioservicio = servicio;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        if(usuarioservicio.existePorEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }
        // Corrige la forma de construir el objeto 'User'
        Usuario user = new Usuario();
        user.setNick(request.getNick());
        user.setNombre(request.getNombre());
        user.setApellidos(request.getApellidos());
        user.setEmail(request.getEmail());
        user.setFechaNac(request.getFechaNac());
        if (request.getFotoperfil() != null) {
			user.setFotoperfil(request.getFotoperfil());
		}
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(Role.ROLE_USER); // Asegúrate de que Role.USER esté definido correctamente
        usuarioservicio.crearUsuario(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse signin(SigninRequest request) {
        // Maneja la autenticación
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        
       // SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario user = usuarioservicio.encontrarPorEmail(request.getEmail());
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
