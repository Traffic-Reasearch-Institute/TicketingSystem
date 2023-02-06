package com.large.ticketsystem.reservation.controller;

import com.large.ticketsystem.reservation.model.dto.ReservationDetailResponseDto;
import com.large.ticketsystem.reservation.model.dto.ReservationResponseDto;
import com.large.ticketsystem.reservation.service.ReservationService;
import com.large.ticketsystem.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    //예약 상세페이지
    @GetMapping("/reservation/{reservationId}")
    public ReservationDetailResponseDto getReservation(@PathVariable Long reservationId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return reservationService.getReservation(reservationId, userDetails.getMember().getId());
    }

    @GetMapping("/reservation")
    public List<ReservationResponseDto> getReservationList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return reservationService.getReservationList(userDetails.getMember().getId());
    }
}
