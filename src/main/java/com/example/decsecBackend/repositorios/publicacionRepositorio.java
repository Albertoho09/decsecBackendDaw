package com.example.decsecBackend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.decsecBackend.modelo.Publicacion;

@Repository
public interface publicacionRepositorio extends JpaRepository<Publicacion, Long> {

    @Modifying
    @Query("UPDATE publicacion p SET p.megusta = p.megusta + 1 WHERE p.id = :id")
    Publicacion meGusta(@Param("id") Long id);

    @Modifying
    @Query("UPDATE publicacion p SET p.megusta = p.megusta - 1 WHERE p.id = :id")
    Publicacion noMeGusta(@Param("id") Long id);
}
