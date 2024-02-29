package com.example.decsecBackend.controladores;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.decsecBackend.modelo.Publicacion;
import com.example.decsecBackend.modelo.Role;
import com.example.decsecBackend.modelo.Usuario;
import com.example.decsecBackend.serviciosImpl.PublicacionServicioImpl;
import com.example.decsecBackend.serviciosImpl.UsuarioServicioImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/publicaciones")
public class PublicacionController {
    private static final Logger logger = LoggerFactory.getLogger(PublicacionController.class);

    @Autowired
    private PublicacionServicioImpl publicacionService;

    @Autowired
    private UsuarioServicioImpl usuarioService;

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
    public ResponseEntity<?> listarPublicaciones(@PathVariable(required = false) String email,
            @AuthenticationPrincipal Usuario usuario) {

        if (usuario.getRoles().contains(Role.ROLE_ADMIN)) {
            if (email != null) {
                logger.info("##### LISTANDO PUBLICACIONES USUARIO (ADMINISTRADOR) #####");
                return ResponseEntity.ok(publicacionService.listarPublicacionesUsuario(email));
            } else {
                logger.info("##### LISTANDO PUBLICACIONES (ADMINISTRADOR) #####");
                return ResponseEntity.ok(publicacionService.listarPublicaciones());
            }
        } else {
            if (email != null) {
                if (usuarioService.usuarioPrivado(email)) {
                    logger.info("##### PUBLICACIONES NO DISPONIBLE USUARIO PRIVADO (USUARIO) #####");
                    return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
                            .body("Publicaciones no disponibles, usuario privado.");
                } else {
                    logger.info("##### LISTANDO PUBLICACIONES USUARIO (USUARIO) #####");
                    return ResponseEntity.ok(publicacionService.listarPublicacionesUsuario(email));
                }
            } else {
                logger.info("##### LISTANDO PUBLICACIONES PROPIAS (USUARIO) #####");
                return ResponseEntity.ok(publicacionService.listarPublicacionesUsuario(usuario.getEmail()));
            }
        }

    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> crearPublicacion(@RequestBody Publicacion publicacion) {
        try {
            logger.info("##### CREANDO PUBLICACION (USUARIO) #####");
            return ResponseEntity.status(HttpStatus.CREATED).body(publicacionService.crearPublicacion(publicacion));
        } catch (Exception e) {
            logger.info("##### CREACION DE PUBLICACION FALLIDO (USUARIO) #####");
            return ResponseEntity.badRequest().body("Faltan valores o estos son erroneos");
        }
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> actualizarParcialmente(@PathVariable(required = true) Long id,
            @AuthenticationPrincipal Usuario usuario, @RequestBody Map<String, Object> updates) {

        if (publicacionService.pertenecePublicacion(id, usuario.getEmail())) {
            logger.info("##### ACTUALIZANDO PUBLICACION (USUARIO) #####");
            return ResponseEntity.ok(publicacionService.actualizarPublicacion(id, updates));
        } else {
            logger.info("##### ACTUALIZANDO PUBLICACION FALLIDA (USUARIO) #####");
            return ResponseEntity.badRequest().body("La publicacion que quieres editar no te pertenece");
        }

    }
}
