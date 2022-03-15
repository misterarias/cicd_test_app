package com.example.demo.entities;

import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UsuarioLogin {
    private @Id @GeneratedValue Long id;
    private final Date lastLogin ;
    private final String loginToken;

    @OneToOne
    @JoinColumn(name = "user_id_name")
    private Usuario userId;

    UsuarioLogin() {
        lastLogin = null;
        loginToken = null;
    }

    public UsuarioLogin(Usuario loginUser) {
        this.userId = loginUser;
        this.lastLogin = new Date();
        this.loginToken = new RandomString().nextString();
    }

    public Long getId() {
        return userId.getId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "userId = " + userId + ")";
    }

    public Boolean checkLogin(String token) {
        boolean tokenIsValid = token.equals(this.loginToken);
        long loginAgeInSeconds = (new Date().getTime() - this.lastLogin.getTime()) / 1000 ;
        return tokenIsValid && loginAgeInSeconds < 60 ; // One minute
    }

    public String getLoginToken() {
        return loginToken;
    }
}
