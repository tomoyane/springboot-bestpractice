package com.bestpractice.api.domain.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Users {

    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
}
