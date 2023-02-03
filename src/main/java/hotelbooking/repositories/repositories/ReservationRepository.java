package hotelbooking.repositories.repositories;

import hotelbooking.models.pojo.Reservation;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository {
    Reservation createReservation(Reservation reservation);
    Reservation getReservation(Integer id);
    List<Reservation> getReservations();
    void deleteReservation(Integer id);
}
