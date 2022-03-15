package com.example.demo;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Usuario("Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Usuario    ("Frodo Baggins", "thief")));
        };
    }
}