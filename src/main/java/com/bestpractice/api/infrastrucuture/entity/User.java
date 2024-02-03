package com.bestpractice.api.infrastrucuture.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class User extends SharedData implements Serializable {

    @Column(name = "id")
    private String  id;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "email")
    private String email;

    @NotNull
    @Column(nullable = false, name = "password")
    private String password;

    public User() {
    }

    public User(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
