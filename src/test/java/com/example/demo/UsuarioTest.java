package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioTest {

    @Test
    public void testUsuarioCreation() {
        Usuario usuario1 = new Usuario("juan", "1234");
        assertFalse(usuario1.checkPassword("12345"));
        assertTrue(usuario1.checkPassword("1234"));
    }
}