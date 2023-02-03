package hotelbooking.services.services;

import hotelbooking.models.dto.ReservationDto;
import hotelbooking.models.inputs.ReservationInput;

import java.sql.Date;
import java.util.List;

public interface ReservationService {

    ReservationDto createReservation (ReservationInput reservationInput);
    ReservationDto getReservation (Integer id);
    List<ReservationDto> getReservations ();
    void deleteReservation (Integer id);
}
