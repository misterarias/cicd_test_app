package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {
    private @Id @GeneratedValue Long id;
    private String name;
    private int password;

    public Usuario() {}

    public Usuario(String name, String password) {
        this.name = name;
        this.password = password.hashCode();
    }

    public Boolean checkPassword(String password) {
        return password.hashCode() == this.password ;
    }

    public String getName() {
        return this.name;
    }
}
