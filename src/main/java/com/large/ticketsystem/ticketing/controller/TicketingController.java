package com.large.ticketsystem.ticketing.controller;

import com.large.ticketsystem.ticketing.model.dto.SeatsResponseDto;
import com.large.ticketsystem.ticketing.service.TicketingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TicketingController {

    private final TicketingService ticketingService;


    // 좌석 정보 가져오기
    @GetMapping("/seats/{showId}")
    public List<SeatsResponseDto> getSeats(@PathVariable Long showId) {
        return ticketingService.getSeats(showId);
    }

    @PostMapping("/seats/{showId}")
    public ResponseEntity<String> reservationSeats(@RequestBody List<Long> seats, @PathVariable Long showId) { //todo 좌석 번호들을 어떻게 넘길건지 다시 정하기
        ticketingService.reservationSeats(seats, showId);
        return new ResponseEntity<>("예약 성공", HttpStatus.CREATED);
    }
}
