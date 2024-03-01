package com.example.decsecBackend.modelo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	private byte[] foto;

	@NotNull
	private String comentarioUsuario;

	private int megusta = 0;

	private LocalDate fechaPublicacion;

	@ManyToOne
	@ElementCollection(fetch = FetchType.LAZY, targetClass = Usuario.class)
	@JoinColumn(name = "usuario_id", nullable = false)
	@JsonManagedReference
	private Usuario usuario;

}
