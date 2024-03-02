package com.example.decsecBackend.serviciosImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.decsecBackend.dtos.ComentarioDTO;
import com.example.decsecBackend.repositorios.ComentarioRepositorio;
import com.example.decsecBackend.servicios.ComentarioServicio;

@Service
public class ComentarioServicioImpl implements ComentarioServicio {

    @Autowired
    private ComentarioRepositorio repositorioComentario;

    @Override
    public List<ComentarioDTO> listarComentarios() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarComentarios'");
    }

    @Override
    public List<ComentarioDTO> listarComentariosPublicacion(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarComentariosPublicacion'");
    }

    @Override
    public List<ComentarioDTO> listarMisComentarios(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarMisComentarios'");
    }

    @Override
    public ComentarioDTO crearComentario(Map<String, Object> datos, String emailUsuario, Long idPubli) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearComentario'");
    }

    @Override
    public ComentarioDTO actualizarComentario(Map<String, Object> datos, Long idComentario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarComentario'");
    }

    @Override
    public ComentarioDTO borrarComentario(Long idComentario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarComentario'");
    }

}
