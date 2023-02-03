package hotelbooking.controllers;

import hotelbooking.config.jwt.UserTokenIdManager;
import hotelbooking.exceptions.AccessDeniedException;
import hotelbooking.models.dto.RoomDto;
import hotelbooking.models.inputs.RoomInput;
import hotelbooking.services.services.RoomService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserTokenIdManager userTokenIdManager;

    @PostMapping
    public RoomDto createRoom(@RequestBody RoomInput roomInput, @RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        return roomService.createRoom(roomInput);
    }

    @GetMapping("/{id}")
    public RoomDto getRoom(@PathVariable Integer id) {

        return roomService.getRoom(id);
    }

    @GetMapping
    public List<RoomDto> getRooms(){

        return roomService.getRooms();
    }

    @DeleteMapping
    public void deleteRoom(@RequestParam Integer id, @RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        roomService.deleteRoom(id);
    }

    @GetMapping("/hotel")
    public List<RoomDto> getHotelRoomsByHotel (@RequestParam String hotelName) {

        return roomService.getRoomsByHotel(hotelName);
    }

}
