package com.sparta.jpaschedule.entity;

import lombok.Getter;

@Getter

public class UsersInfo {
    private Long id;
    private String name;
    private String email;

    public UsersInfo(Long id, String username, String email) {
        this.id = id;
        this.name = username;
        this.email = email;
    }
}
