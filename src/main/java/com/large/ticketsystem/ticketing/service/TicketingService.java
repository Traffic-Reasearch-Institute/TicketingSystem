package com.large.ticketsystem.ticketing.service;

import com.large.ticketsystem.reservation.model.Reservation;
import com.large.ticketsystem.reservation.repository.ReservationRepository;
import com.large.ticketsystem.ticketing.model.ReservedSeats;
import com.large.ticketsystem.ticketing.model.Seats;
import com.large.ticketsystem.ticketing.model.dto.SeatsResponseDto;
import com.large.ticketsystem.ticketing.repository.ReservedSeatsRepository;
import com.large.ticketsystem.ticketing.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TicketingService {

    private final SeatsRepository seatsRepository;
    private final ReservedSeatsRepository seatReservationRepository;
    private final ReservationRepository reservationRepository;

    @PostConstruct //todo 없애기
    public void init() {

        for (int i = 1; i <= 30; i++) {
            Seats seat = new Seats(1L, "A" + i);
            seatsRepository.save(seat);
        }

        for (int i = 1; i <= 30; i++) {
            Seats seat = new Seats(2L, "B" + i);
            seatsRepository.save(seat);
        }


    }

    //좌석 정보 가져오기
    @Transactional(readOnly = true)
    public List<SeatsResponseDto> getSeats(Long showId) {

//        //해당 공연의 좌석 리스트 가져오기
        List<Seats> seats = seatsRepository.findAllByShowId(showId);

        //dto로 변환하고 좌석 id순서대로 정렬
        return seats.stream().map(seat -> SeatsResponseDto.builder()
                        .seatId(seat.getSeatId())
                        .seatNum(seat.getSeatNum())
                        .status(seat.isStatus())
                        .build())
                .sorted(Comparator.comparing(SeatsResponseDto::getSeatId))
                .collect(Collectors.toList());

//        return seatsRepository.findByShowId(showId);
    }

    // 좌석 예매하기
    @Transactional
    public void reservationSeats(String seats, Long showId) {
        //todo memberId 가져오기
        Long memberId = 5L;

        //좌석번호가 "seats" : "5 6 7" -> 이런 식으로 들어오기 때문에 쪼개서 5,6,7을 List<Long> 안에 담아주도록 한다
        String[] split = seats.split(":\"")[1].split(" ");
        ArrayList<Long> seatList = new ArrayList<>();

        for (int i = 0; i < split.length - 1; i++) {
            System.out.println("i = " + split[i]);
            seatList.add(Long.valueOf(split[i]));
        }

        //선택한 좌석 중 예약된 좌석이 있는지 확인
        List<ReservedSeats> reservedSeats = seatReservationRepository.findAllBySeatIdInAndStatus(seatList, true);
        if (reservedSeats.size() > 0) {
            throw new IllegalArgumentException("이미 예약된 좌석입니다"); //todo 커스텀 예외로 바꿔주기(TicketingException)
        }

        //선택한 좌석번호가 존재하지 않는 경우
        List<Seats> findSeats = seatsRepository.findAllByShowIdAndSeatIdIn(showId, seatList);
        if (findSeats.size() != seatList.size()) {
            throw new IllegalArgumentException("존재하지 않는 좌석입니다"); //todo 커스텀 예외로 바꿔주기(TicketingException)
        }

        //예약내역을 예약 테이블에 먼저 저장 //todo 수정할 것
        Reservation reservation = new Reservation(showId, memberId);
        Reservation savedReservation = reservationRepository.save(reservation);


        //선택한 좌석들을 예약좌석 테이블에 저장
        for (Long seat : seatList) {
            ReservedSeats reservedSeat = new ReservedSeats(seat, savedReservation.getId());
            seatReservationRepository.save(reservedSeat);
        }

        // 좌석 테이블에 status -> true(예약됨) 으로 바꿔주기
        seatsRepository.updateStatus(seatList);
    }
}
