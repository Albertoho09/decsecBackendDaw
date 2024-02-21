package com.example.decsecBackend.servicios;

import java.util.List;
import java.util.Map;

import com.example.decsecBackend.modelo.Publicacion;

public interface publicacionServicio {

    Publicacion crearPublicacion(Publicacion publicacion);

    List<Publicacion> listarPublicaciones();

    List<Publicacion> listarPublicacionesUsuario(String email);

    Publicacion listarPublicacionPorId(Long id);

    List<Publicacion> listarPublicacionesdFeed(Long id);

    void borrarPublicacion(Long id);

    Publicacion actualizarPublicacion(Long id, Map<String, Object> updates);

    Boolean existePorId(Long id);

    Publicacion megusta(Long id);

    Publicacion noMegusta(Long id);

}
