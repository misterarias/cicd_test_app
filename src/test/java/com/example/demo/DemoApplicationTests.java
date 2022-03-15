package com.example.demo;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoApplicationTests {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        repository.deleteAll();
        repository.save(new Usuario("juan", "123456"));
        repository.save(new Usuario("jorge", "abcdef"));
    }

    @Test
    void testCorrectLogin() throws Exception {
        mockMvc.perform(
                post("/login")
                        .contentType("application/json")
                        .content("{\"name\": \"juan\", \"password\": \"123456\"}")
        ).andExpect(
                status().isOk()
        ).andExpect(
                header().string("X-Login-Token", repository.findByName("juan").getLoginToken() )
        );
    }

    @Test
    void testUnknownUserLogin() throws Exception {
        mockMvc.perform(
                post("/login")
                        .contentType("application/json")
                        .content("{\"name\": \"manolo\", \"password\": \"123\"}")
        ).andExpect(
                status().is(401)
        ).andExpect(
                header().doesNotExist("X-Login-Token")
        );
    }

    @Test
    void testInvalidPasswordLogin() throws Exception {
        mockMvc.perform(
                post("/login")
                        .contentType("application/json")
                        .content("{\"name\": \"juan\", \"password\": \"123\"}")
        ).andExpect(
                status().is(401)
        ).andExpect(
                header().doesNotExist("X-Login-Token")
        );
    }

    @Test
    void testGetUsuarios() throws Exception {
        mockMvc.perform(
                get("/usuarios")
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().json("[\"juan\",\"jorge\"]")
        );
    }
}
