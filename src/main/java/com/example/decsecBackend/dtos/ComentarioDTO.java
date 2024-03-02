package com.example.decsecBackend.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ComentarioDTO {

    private Long id;

    private String comentario;

    private LocalDateTime hora;

    private String nickUsuario;

    public ComentarioDTO(String comentario, LocalDateTime hora, String nickUsuario, Long id) {
        this.id = id;
        this.comentario = comentario;
        this.hora = hora;
        this.nickUsuario = nickUsuario;
    }
}
