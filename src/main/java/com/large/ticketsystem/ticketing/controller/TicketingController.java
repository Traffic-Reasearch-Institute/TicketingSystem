package com.large.ticketsystem.ticketing.controller;

import com.large.ticketsystem.ticketing.model.dto.SeatsResponseDto;
import com.large.ticketsystem.ticketing.service.TicketingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TicketingController {

    private final TicketingService ticketingService;

    /*
       method: 좌석 정보 가져오기
       param : 공연 id
     */
    @GetMapping("/seats/{showId}")
    public List<SeatsResponseDto> getSeats(@PathVariable Long showId) {
        return ticketingService.getSeats(showId);
    }
}
