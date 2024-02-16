package com.example.decsecBackend.configuracion;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.decsecBackend.modelo.Usuario;
import com.example.decsecBackend.modelo.Role;
import com.example.decsecBackend.serviciosImpl.usuarioServicioImpl;

@Component
public class inicializarDatos implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private usuarioServicioImpl usuarioservicio;

    private Boolean crearDatos = true;

    @Override
    public void run(String... args) throws Exception {
        try {

            if (crearDatos) {
                Usuario usu1 = new Usuario();
                usu1.setNick("Raikeor");
                usu1.setNombre("Alberto");
                usu1.setApellidos("Hidalgo");
                usu1.setFechaNac(LocalDate.now());
                usu1.setEmail("correo@gmail.com");
                usu1.setPassword(passwordEncoder.encode("melva"));
                usu1.getRoles().add(Role.ROLE_ADMIN);

                usuarioservicio.crearUsuario(usu1);

                Usuario usu2 = new Usuario();
                usu2.setNick("Revived");
                usu2.setNombre("Andrés");
                usu2.setApellidos("Dominguez");
                usu2.setFechaNac(LocalDate.now());
                usu2.setEmail("otrocorreo@gmail.com");
                usu2.setPassword(passwordEncoder.encode("tomate"));
                usu2.getRoles().add(Role.ROLE_USER);

                usuarioservicio.crearUsuario(usu2);

                Usuario usu3 = new Usuario();
                usu3.setNick("Kidi");
                usu3.setNombre("David");
                usu3.setApellidos("Rodriguez");
                usu3.setFechaNac(LocalDate.now());
                usu3.setEmail("correonuevo@gmail.com");
                usu3.setPassword(passwordEncoder.encode("muhe"));
                usu3.getRoles().add(Role.ROLE_ADMIN);

                usuarioservicio.crearUsuario(usu3);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
