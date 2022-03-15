package com.example.demo.controllers;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<String> getUsuarios() {
        return repository.findAll().stream().map(Usuario::getName).collect(Collectors.toList());
    }

    @PostMapping
    public Usuario add(@RequestBody Usuario nuevoUsuario) {
        log.info(String.format("Recibido usuario: %s", nuevoUsuario));
        return repository.save(nuevoUsuario);
    }
}
