package com.large.ticketsystem.ticketing.repository;

import com.large.ticketsystem.ticketing.model.Seats;
import com.large.ticketsystem.ticketing.model.dto.SeatsResponseDto;

import java.util.List;

public interface CustomSeatsRepository {
    List<String> findByReservationId(Long reservationId);

    List<SeatsResponseDto> findByShowId(Long showId);

    void updateStatus(List<Long> seats);
}
