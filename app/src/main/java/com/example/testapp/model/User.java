package com.example.testapp.model;

import java.time.LocalDateTime;

public class User {
    private Long id;

    private Integer points;
    private String username, password, role_name, token;

    private LocalDateTime create_at, update_at;
    private boolean isActive;

    public User() {
        super();
    }

    public User(Long id, Integer points, String username, String password, String role_name, String token, LocalDateTime create_at, LocalDateTime update_at, boolean isActive) {
        this.id = id;
        this.points = points;
        this.username = username;
        this.password = password;
        this.role_name = role_name;
        this.token = token;
        this.create_at = create_at;
        this.update_at = update_at;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public LocalDateTime getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(LocalDateTime update_at) {
        this.update_at = update_at;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

