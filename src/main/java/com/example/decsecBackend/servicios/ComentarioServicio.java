package com.example.decsecBackend.servicios;

import java.util.List;
import java.util.Map;

import com.example.decsecBackend.dtos.ComentarioDTO;

public interface ComentarioServicio {

    List<ComentarioDTO> listarComentarios();

    List<ComentarioDTO> listarComentariosPublicacion(Long id);

    List<ComentarioDTO> listarMisComentarios(String email);

    ComentarioDTO crearComentario(Map<String, Object> datos, String emailUsuario, Long idPubli);

    ComentarioDTO actualizarComentario(Map<String, Object> datos, Long idComentario);

    ComentarioDTO borrarComentario(Long idComentario);

}
