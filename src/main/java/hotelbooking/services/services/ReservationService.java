package hotelbooking.services.services;

import hotelbooking.models.dto.ReservationDto;

import java.sql.Date;
import java.util.List;

public interface ReservationService {

    ReservationDto createReservation (Integer roomId, String email, Date startDate, Date endDate);
    ReservationDto getReservation (Integer id);
    List<ReservationDto> getReservations ();
    void deleteReservation (Integer id);
}
