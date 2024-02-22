package com.example.decsecBackend.serviciosImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.decsecBackend.errores.NotFoundException;
import com.example.decsecBackend.modelo.Publicacion;
import com.example.decsecBackend.modelo.Usuario;
import com.example.decsecBackend.repositorios.publicacionRepositorio;
import com.example.decsecBackend.servicios.publicacionServicio;

@Service
public class publicacionServicioImpl implements publicacionServicio {

    @Autowired
    private publicacionRepositorio repositorioPublicacion;
    @Autowired
    private usuarioServicioImpl servicioUsuario;

    @SuppressWarnings("null")
    @Override
    public Publicacion crearPublicacion(Publicacion publicacion) {
        return repositorioPublicacion.save(publicacion);
    }

    @Override
    public List<Publicacion> listarPublicaciones() {
        return repositorioPublicacion.findAll();
    }

    @Override
    public List<Publicacion> listarPublicacionesUsuario(String email) {
        return servicioUsuario.encontrarPorEmail(email).getPublicaciones();
    }

    @SuppressWarnings("null")
    @Override
    public Publicacion listarPublicacionPorId(Long id) {
        return repositorioPublicacion.findById(id)
                .orElseThrow(() -> new NotFoundException("Publicacion no encontrada"));
    }

    @Override
    public List<Publicacion> listarPublicacionesdFeed(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'listarPublicacionesdFeed'");
    }

    @SuppressWarnings("null")
    @Override
    public void borrarPublicacion(Long id) {
        repositorioPublicacion.deleteById(id);
    }

    @SuppressWarnings("null")
    @Override
    public Publicacion actualizarPublicacion(Long id, Map<String, Object> updates) {

        Publicacion publi = repositorioPublicacion.findById(id)
                .orElseThrow(() -> new NotFoundException("Publicacion no encontrada"));

        updates.forEach((campo, valor) -> {
            switch (campo) {
                case "comentarioUsuario":
                    publi.setComentarioUsuario(valor.toString());
                    break;

                default:
                    break;
            }
        });

        return repositorioPublicacion.save(publi);
    }

    @SuppressWarnings("null")
    @Override
    public Boolean existePorId(Long id) {
        return repositorioPublicacion.existsById(id);
    }

    @Override
    public void megusta(Long id) {
        repositorioPublicacion.meGusta(id);
    }

    @Override
    public void noMegusta(Long id) {
        repositorioPublicacion.noMeGusta(id);
    }

    @Override
    public Boolean pertenecePublicacion(Long id, String email) {
        
        List<Publicacion> publicaciones = servicioUsuario.encontrarPorEmail(email).getPublicaciones();

        return publicaciones.stream()
        .anyMatch(objeto -> objeto.getId() == id);

    }
    

}
