package com.large.ticketsystem.ticketing.controller;

import com.large.ticketsystem.ticketing.model.dto.SeatsResponseDto;
import com.large.ticketsystem.ticketing.service.TicketingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class TicketingController {

    private final TicketingService ticketingService;

    // 좌석 정보 가져오기
    @GetMapping("/seats/{showId}")
    public String getSeats(@PathVariable Long showId, Model model) {
        model.addAttribute("seats", ticketingService.getSeats(showId));
        return "seats";
//        return ticketingService.getSeats(showId);
    }

    @PostMapping("/seats/{showId}")
    @ResponseBody
    public ResponseEntity<String> reservationSeats(@RequestBody String seats, @PathVariable Long showId) { //todo 좌석 번호들을 어떻게 넘길건지 다시 정하기
        log.info("TicketingController - 예약하기 실행");

        ticketingService.reservationSeats(seats, showId);
        return new ResponseEntity<>("예약 성공", HttpStatus.CREATED);
    }

}
