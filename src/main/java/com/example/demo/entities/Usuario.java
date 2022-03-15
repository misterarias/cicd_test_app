package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Usuario {
    private @Id @GeneratedValue Long id;
    private String name;
    private int password;

    @OneToOne
    private UsuarioLogin userLogin ;

    public Usuario() {
    }

    public Usuario(String name, String password) {
        this.name = name;
        this.password = password.hashCode();
        this.userLogin = null;
    }

    public Boolean checkPassword(String password) {
        return password.hashCode() == this.password;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Long getId() {
        return this.id;
    }

    public void setUserLogin(UsuarioLogin userLogin) {
        this.userLogin = userLogin;
    }

    public UsuarioLogin getUserLogin() {
        return userLogin;
    }
}
