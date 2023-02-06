package com.large.ticketsystem.reservation.repository;

import com.large.ticketsystem.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByIdAndMemberId(Long reservationId, Long memberId);

    List<Reservation> findAllByMemberId(Long memberId);
}
