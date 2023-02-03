package com.large.ticketsystem.ticketing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Seats {
    @Id @GeneratedValue
    private Long seatId;
    private Long showId; //todo show - 연관관계로 바꿔주기
    private String seatNum;
    private boolean status;
}
