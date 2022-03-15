package com.example.demo.entities;

import net.bytebuddy.utility.RandomString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Usuario {
    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private int password;
    private Date lastLogin;
    private String loginToken;

    public Usuario() {
    }

    public Usuario(String name, String password) {
        this.name = name;
        this.password = password.hashCode();
        lastLogin = null;
        loginToken = null;
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

    public String doLogin() {
        lastLogin = new Date();
        loginToken = new RandomString().nextString();
        return loginToken;
    }

    public Boolean checkLogin(String token) {
        boolean tokenIsValid = token.equals(this.loginToken);
        long loginAgeInSeconds = (new Date().getTime() - this.lastLogin.getTime()) / 1000;
        return tokenIsValid && loginAgeInSeconds < 60; // One minute
    }

    public String getLoginToken() {
        return this.loginToken;
    }
}
