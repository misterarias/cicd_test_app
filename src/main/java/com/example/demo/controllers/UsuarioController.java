package com.example.demo.controllers;

import com.example.demo.Usuario;
import com.example.demo.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {

    private final UsuarioRepository repository ;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return repository.findAll();
    }
}
