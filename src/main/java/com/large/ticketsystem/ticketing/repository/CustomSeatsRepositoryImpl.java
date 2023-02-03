package com.large.ticketsystem.ticketing.repository;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
