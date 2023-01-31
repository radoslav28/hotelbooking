package hotelbooking.repositories.repositories;

import hotelbooking.models.pojo.Reservation;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository {
    Reservation createReservation(Integer roomId, Integer userId, Double roomPrice, Date startDate, Date endDate);
    Reservation getReservation(Integer id);
    List<Reservation> getReservations();
    void deleteReservation(Integer id);
}
