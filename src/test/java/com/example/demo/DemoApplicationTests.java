package com.example.demo;

import com.example.demo.controllers.LoginController;
import com.example.demo.controllers.UsuarioController;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UsuarioController userController;

    @Autowired
    LoginController loginController;

    @Autowired
    UsuarioRepository repository;

    @Test
    void testLogin() {
        ResponseEntity<Usuario> response = loginController.login(
                new LoginController.LoginUserPayload("Bilbo Baggins", "1234")
        );
        assertEquals(
                response.getHeaders().getFirst("X-Login-Token"),
                repository.findByName("Bilbo Baggins").getLoginToken()
        );
    }

    @Test
    void testGetUsuarios() {
        assertEquals(
                userController.getUsuarios().stream().map(Usuario::getName).collect(Collectors.toList()),
                List.of(new String[]{"Bilbo Baggins", "Frodo Baggins"})
        );
    }
}
