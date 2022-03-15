package com.example.demo;

import com.example.demo.controllers.LoginController;
import com.example.demo.controllers.UsuarioController;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoApplicationTests {

    @Autowired
    UsuarioController userController;

    @Autowired
    LoginController loginController;

    @Autowired
    UsuarioRepository repository;

    @BeforeEach
    public void init() {
        repository.deleteAll();
        repository.save(new Usuario("juan", "123456"));
        repository.save(new Usuario("jorge", "abcdef"));
    }

    @Test
    void testLogin() {
        ResponseEntity<Usuario> response = loginController.login(
                new LoginController.LoginUserPayload("juan", "123456")
        );
        assertEquals(
                response.getHeaders().getFirst("X-Login-Token"),
                repository.findByName("juan").getLoginToken()
        );
    }

    @Test
    void testGetUsuarios() {
        assertEquals(
                userController.getUsuarios().stream().map(Usuario::getName).collect(Collectors.toList()),
                List.of(new String[]{"juan", "jorge"})
        );
    }
}
