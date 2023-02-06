package com.large.ticketsystem.ticketing.repository;

import com.large.ticketsystem.ticketing.model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatsRepository extends JpaRepository<Seats, Long>, CustomSeatsRepository {

    List<Seats> findAllByShowId(Long showId);

    List<Seats> findAllByShowIdAndSeatIdIn(Long showId, List<Long> seats);
}
