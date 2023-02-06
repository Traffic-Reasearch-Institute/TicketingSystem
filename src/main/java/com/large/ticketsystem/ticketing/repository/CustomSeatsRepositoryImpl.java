package com.large.ticketsystem.ticketing.repository;

import com.large.ticketsystem.ticketing.model.dto.SeatsResponseDto;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
public class CustomSeatsRepositoryImpl implements CustomSeatsRepository{

    @PersistenceContext
    private final EntityManager em;
    @Override
    public List<String> findByReservationId(Long reservationId) {


        TypedQuery<String> query = em.createQuery("select s.seatNum from Seats s left join ReservedSeats r on s.seatId = r.seatId "
                + "where r.reservationId = :reservationId", String.class);

        query.setParameter("reservationId", reservationId);
        return query.getResultList();
    }

    @Override
    public List<SeatsResponseDto> findByShowId(Long showId) {

        TypedQuery<SeatsResponseDto> query = em.createQuery("select s.seatId, s.seatNum, r.status from Seats s left join ReservedSeats r on s.seatId = r.seatId "
                + "where s.showId = :showId", SeatsResponseDto.class);

        query.setParameter("showId", showId);
        return query.getResultList();
    }

    //좌석 예약후 좌석정보 예약중으로 상태 변경하기
    @Override
    public void updateStatus(List<Long> seats) {
        Query query = em.createQuery("update Seats s set s.status = true where s.id in (:seats)");
        query.setParameter("seats", seats);
        query.executeUpdate();

    }
}
