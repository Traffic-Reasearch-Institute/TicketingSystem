package com.large.ticketsystem.reservation.controller;

import com.large.ticketsystem.reservation.model.dto.ReservationResponseDto;
import com.large.ticketsystem.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservation/{reservationId}/{memberId}") //todo pathVariable memberId 빼고 Authentication으로 교체
    public ReservationResponseDto getReservation(@PathVariable Long reservationId, @PathVariable Long memberId) {
        //todo memberId -> Authentication 정보로 변경

        return reservationService.getReservation(reservationId, memberId);
    }
}
