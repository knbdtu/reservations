package com.knbdtu.reservations.data.repository;

import com.knbdtu.reservations.data.entity.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    @Query(value = "SELECT * FROM ROOM WHERE ROOM_ID = ?1", nativeQuery = true)
    Room findByNumber(String roomNumber);
}
