package com.knbdtu.reservations.data.repository;

import com.knbdtu.reservations.data.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query(value = "SELECT * FROM RESERVATION WHERE RES_DATE = ?", nativeQuery = true)
    List<Reservation> findByDate(Date date);
}
