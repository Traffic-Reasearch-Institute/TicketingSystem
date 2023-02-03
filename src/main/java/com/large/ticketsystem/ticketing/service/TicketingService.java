package com.large.ticketsystem.ticketing.service;

import com.large.ticketsystem.ticketing.model.Seats;
import com.large.ticketsystem.ticketing.model.dto.SeatsResponseDto;
import com.large.ticketsystem.ticketing.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TicketingService {

    private final SeatsRepository seatsRepository;

    @PostConstruct
    public void init() {

        for (int i = 0; i < 30; i++) {
            Seats seat = new Seats(1L, "A" + i);
            seatsRepository.save(seat);
        }

        for (int i = 0; i < 30; i++) {
            Seats seat = new Seats(2L, "B" + i);
            seatsRepository.save(seat);
        }
    }

    //좌석 정보 가져오기
    public List<SeatsResponseDto> getSeats(Long showId) {

        //해당 공연의 좌석 리스트 가져오기
        List<Seats> seats = seatsRepository.findByShowId(showId);

        //dto로 변환하고 좌석 id순서대로 정렬
        return seats.stream().map(seat -> SeatsResponseDto.builder()
                        .seatId(seat.getSeatId())
                        .seatNum(seat.getSeatNum())
                        .status(seat.isStatus())
                        .build())
                .sorted(Comparator.comparing(SeatsResponseDto::getSeatId))
                .collect(Collectors.toList());
    }
}
