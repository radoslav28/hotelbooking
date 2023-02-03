package hotelbooking.controllers;

import hotelbooking.config.jwt.UserTokenIdManager;
import hotelbooking.exceptions.AccessDeniedException;
import hotelbooking.models.dto.ReservationDto;
import hotelbooking.models.inputs.ReservationInput;
import hotelbooking.services.services.ReservationService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserTokenIdManager userTokenIdManager;

    @PostMapping
    public ReservationDto createReservation(@RequestBody ReservationInput reservationInput) {
        return reservationService.createReservation(reservationInput);
    }

    @GetMapping("/{id}")
    public ReservationDto getReservation(@PathVariable Integer id) {

        return reservationService.getReservation(id);
    }

    @GetMapping
    public List<ReservationDto> getReservations(@RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        return reservationService.getReservations();
    }

    @DeleteMapping
    public void deleteReservation(@RequestParam Integer id) {

        reservationService.deleteReservation(id);
    }
}
