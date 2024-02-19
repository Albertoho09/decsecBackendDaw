package com.example.decsecBackend.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Publicacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	@NotNull
	private byte[] foto;

	@NotNull
	private String comentarioUsuario;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

}
