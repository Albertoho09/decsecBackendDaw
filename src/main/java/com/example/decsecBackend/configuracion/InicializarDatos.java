package com.example.decsecBackend.configuracion;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.decsecBackend.modelo.Usuario;
import com.example.decsecBackend.modelo.Publicacion;
import com.example.decsecBackend.modelo.Role;
import com.example.decsecBackend.serviciosImpl.PublicacionServicioImpl;
import com.example.decsecBackend.serviciosImpl.UsuarioServicioImpl;

@Component
public class InicializarDatos implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioServicioImpl usuarioservicio;
    @Autowired
    private PublicacionServicioImpl publicacionservicio;

    @Override
    public void run(String... args) throws Exception {
        try {

            Usuario usu1 = new Usuario();
            usu1.setNick("Raikeor");
            usu1.setNombre("Alberto");
            usu1.setApellidos("Hidalgo");
            usu1.setFechaNac(LocalDate.now());
            usu1.setEmail("correo@gmail.com");
            usu1.setPassword(passwordEncoder.encode("melva"));
            usu1.setPrivado(false);
            usu1.getRoles().add(Role.ROLE_ADMIN);

            usuarioservicio.crearUsuario(usu1);

            Publicacion publi1 = new Publicacion();
            publi1.setFechaPublicacion(LocalDate.now());
            publi1.setComentarioUsuario("Foto de Andres 1");

            Publicacion publi2 = new Publicacion();
            publi2.setFechaPublicacion(LocalDate.now());
            publi2.setComentarioUsuario("Foto de Andres 2");

            Usuario usu2 = new Usuario();
            usu2.setNick("Revived");
            usu2.setNombre("Andrés");
            usu2.setApellidos("Dominguez");
            usu2.setFechaNac(LocalDate.now());
            usu2.setEmail("otrocorreo@gmail.com");
            usu2.setPassword(passwordEncoder.encode("tomate"));
            usu2.setPrivado(false);
            usu2.getRoles().add(Role.ROLE_USER);

            publi1.setUsuario(usu2);
            publi2.setUsuario(usu2);

            usu2.asignarPublicacion(publi1);
            usu2.asignarPublicacion(publi2);

            usuarioservicio.crearUsuario(usu2);

            Publicacion publi3 = new Publicacion();
            publi3.setFechaPublicacion(LocalDate.now());
            publi3.setComentarioUsuario("Foto de Ana 1");

            Publicacion publi4 = new Publicacion();
            publi4.setFechaPublicacion(LocalDate.now());
            publi4.setComentarioUsuario("Foto de Ana 2");

            Usuario usu3 = new Usuario();
            usu3.setNick("Melva");
            usu3.setNombre("Ana");
            usu3.setApellidos("Cabesita");
            usu3.setFechaNac(LocalDate.now());
            usu3.setEmail("ana@gmail.com");
            usu3.setPassword(passwordEncoder.encode("tomate"));
            usu3.setPrivado(true);
            usu3.getRoles().add(Role.ROLE_USER);

            publi3.setUsuario(usu3);
            publi4.setUsuario(usu3);

            usu3.asignarPublicacion(publi3);
            usu3.asignarPublicacion(publi4);

            usuarioservicio.crearUsuario(usu3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
