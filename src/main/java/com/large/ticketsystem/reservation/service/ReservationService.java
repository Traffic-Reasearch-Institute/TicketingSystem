package com.large.ticketsystem.reservation.service;

import com.large.ticketsystem.reservation.model.Reservation;
import com.large.ticketsystem.reservation.model.dto.ReservationResponseDto;
import com.large.ticketsystem.reservation.repository.ReservationRepository;
import com.large.ticketsystem.ticketing.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatsRepository seatsRepository;

    public ReservationResponseDto getReservation(Long reservationId, Long memberId) {
        //todo 예약자와 회원 일치하는지 확인

        //회원아이디와 예약아이디로 예약내역 찾아온다
        Reservation reservation = reservationRepository.findByIdAndMemberId(reservationId, memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 예약내역입니다")
        );//todo 예외처리

        return ReservationResponseDto.builder()
                .reservationId(reservation.getId())
                .showId(reservation.getShowId())
                .memberId(reservation.getMemberId())
                .createdAt(reservation.getCreatedAt())
                .status(reservation.isStatus())
                .seats(seatsRepository.findByReservationId(reservationId))
                .build();
    }
}
