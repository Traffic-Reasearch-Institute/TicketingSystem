package com.large.ticketsystem.members.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter

public class MemberCachedDto implements Serializable{
    private Long id;
    private String username;

    public MemberCachedDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
