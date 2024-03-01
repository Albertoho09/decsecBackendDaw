package com.example.decsecBackend.dtos;

import java.time.LocalDate;

import com.example.decsecBackend.modelo.Publicacion;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * PublicacionDTO
 */

@Data
public class PublicacionDTO {

    private byte[] foto;

    private String comentarioUsuario;

    private int megusta = 0;

    private LocalDate fechaPublicacion;

    private String emailUsuario;

    public PublicacionDTO(Publicacion publi) {
        this.foto = publi.getFoto();
        this.comentarioUsuario = publi.getComentarioUsuario();
        this.megusta = publi.getMegusta();
        this.fechaPublicacion = publi.getFechaPublicacion();
        this.emailUsuario = publi.getUsuario().getEmail();
    }
}