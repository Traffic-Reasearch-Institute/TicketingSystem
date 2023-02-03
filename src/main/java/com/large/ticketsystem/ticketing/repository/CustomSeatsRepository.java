package com.large.ticketsystem.ticketing.repository;

import com.large.ticketsystem.ticketing.model.Seats;

import java.util.List;

public interface CustomSeatsRepository {
    List<String> findByReservationId(Long reservationId);
}
