package com.example.demo.controllers;

import com.example.demo.entities.Usuario;
import com.example.demo.exceptions.InvalidLoginException;
import com.example.demo.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    public static class LoginUserPayload {
        String name;
        String password;

        public LoginUserPayload(String name, String password) {
            this.name = name;
            this.password = password;
        }
    }

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioRepository repository;

    public LoginController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginUserPayload usuarioLogin) {

        Usuario currentUser = repository.findByName(usuarioLogin.name);
        log.debug("Found person: " + currentUser);

        if (currentUser == null || !currentUser.checkPassword(usuarioLogin.password)) {
            throw new InvalidLoginException();
        }

        String token = currentUser.doLogin();
        repository.save(currentUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Login-Token", token);
        return ResponseEntity.ok().
                headers(responseHeaders).body(currentUser);
    }
}
