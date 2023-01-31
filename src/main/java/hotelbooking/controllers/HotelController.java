package hotelbooking.controllers;

import hotelbooking.config.jwt.UserTokenIdManager;
import hotelbooking.exceptions.AccessDeniedException;
import hotelbooking.models.dto.HotelDto;
import hotelbooking.models.inputs.HotelInput;
import hotelbooking.services.services.HotelService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserTokenIdManager userTokenIdManager;

    @PostMapping
    public HotelDto createHotel(@RequestBody HotelInput hotel, @RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        return hotelService.createHotel(hotel.getHotelName(),hotel.getCityName());
    }

    @GetMapping("/{id}")
    public HotelDto getHotel(@PathVariable Integer id) {

        return hotelService.getHotel(id);
    }

    @GetMapping
    public List<HotelDto> getHotels(){

        return hotelService.getHotels();
    }

    @DeleteMapping
    public void deleteHotel(@RequestParam Integer id, @RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        hotelService.deleteHotel(id);
    }

    @PostMapping("/facility")
    public void giveHotelFacility(@RequestParam String hotelName,@RequestParam String facility, @RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        hotelService.giveHotelFacility(hotelName, facility);
    }

    @GetMapping("/facility")
    public List<HotelDto> getHotelsByFacility (@RequestParam List<String> facilities) {

        return hotelService.getHotelsByFacility(facilities);
    }

    @GetMapping("/city")
    public List<HotelDto> getHotelsByCity (@RequestParam List<String> cities) {

        return hotelService.getHotelsByCity(cities);
    }


}
