package com.large.ticketsystem.reservation.service;

import com.large.ticketsystem.reservation.model.Reservation;
import com.large.ticketsystem.reservation.model.dto.ReservationDetailResponseDto;
import com.large.ticketsystem.reservation.model.dto.ReservationResponseDto;
import com.large.ticketsystem.reservation.repository.ReservationRepository;
import com.large.ticketsystem.ticketing.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatsRepository seatsRepository;

    //예약 상세내역 가져오기
    public ReservationDetailResponseDto getReservation(Long reservationId, Long memberId) {
        //회원아이디와 예약아이디로 예약내역 찾아온다
        Reservation reservation = reservationRepository.findByIdAndMemberId(reservationId, memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 예약내역입니다")
        );//todo 예외처리

        return ReservationDetailResponseDto.builder()
                .reservationId(reservation.getId())
                .showTitle(reservation.getShow().getTitle())
                .memberId(reservation.getMemberId())
                .createdAt(reservation.getCreatedAt())
                .status(reservation.isStatus())
                .seats(seatsRepository.findByReservationId(reservationId))
                .build();
    }

    //예약 전체 리스트 가져오기
    public List<ReservationResponseDto> getReservationList(Long memberId) {
        List<Reservation> reservations = reservationRepository.findAllByMemberId(memberId);

        return reservations.stream().map(reservation -> ReservationResponseDto.builder()
                .reservationId(reservation.getId())
                .showTitle(reservation.getShow().getTitle())
                .memberId(reservation.getMemberId())
                .createdAt(reservation.getCreatedAt())
                .status(reservation.isStatus())
                .build())
                .collect(Collectors.toList());
    }
}
