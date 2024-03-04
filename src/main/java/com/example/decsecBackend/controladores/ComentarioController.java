package com.example.decsecBackend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.decsecBackend.dtos.ComentarioDTO;
import com.example.decsecBackend.dtos.ComentarioDTOrequest;
import com.example.decsecBackend.modelo.Comentario;
import com.example.decsecBackend.modelo.Role;
import com.example.decsecBackend.modelo.Usuario;
import com.example.decsecBackend.serviciosImpl.ComentarioServicioImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioServicioImpl comentarioServicio;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
    public ResponseEntity<?> listarTodosComentarios(@AuthenticationPrincipal Usuario usuario) {
        if (usuario.getRoles().contains(Role.ROLE_ADMIN)) {
            return ResponseEntity.ok(comentarioServicio.listarComentarios());
        } else {
            return ResponseEntity.ok(comentarioServicio.listarMisComentarios(usuario.getEmail()));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
    public ResponseEntity<?> listarComentariosPublicacion(@PathVariable(required = true) Long id,
            @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(comentarioServicio.listarComentariosPublicacion(id));
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
    public ResponseEntity<?> postMethodName(@RequestBody ComentarioDTOrequest comentario,
            @PathVariable(required = true) Long id,
            @AuthenticationPrincipal Usuario usuario) {
        Comentario coment = new Comentario();
        coment.setComentario(comentario.getComentario());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                comentarioServicio.crearComentario(coment, usuario.getEmail(), id));
    }

    @PatchMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        // TODO: process PUT request

        return entity;
    }

}
