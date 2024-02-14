package com.example.decsecBackend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.decsecBackend.modelo.Publicacion;
@Repository
public interface publicacionRepositorio extends JpaRepository<Publicacion, Long>{

}
