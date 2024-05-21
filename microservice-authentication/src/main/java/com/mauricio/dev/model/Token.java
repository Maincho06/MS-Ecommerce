package com.mauricio.dev.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "token")
public class Token {

    @Id
    private String  id;

    private String accessToken;

    private String refreshToken;

    @Column(name = "is_logged_out")
    private boolean loggedOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Token() {
        this.id = UUID.randomUUID().toString();
    }
}