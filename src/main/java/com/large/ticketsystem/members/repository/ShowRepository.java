package com.large.ticketsystem.members.repository;

import com.large.ticketsystem.reservation.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
