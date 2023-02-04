package com.large.ticketsystem.members.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MembersRoleEnum role;

    public Members(String username, String password, MembersRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
