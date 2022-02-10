package com.example.warehousemonitoringsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Date regDate;
    private boolean isBlocked;

    private List<Role> roles;

    public User(int id, String login, String password, String firstName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.isBlocked = false;
        this.roles = Collections.singletonList(new Role(1, "ROLE_ADMIN"));
    }
}