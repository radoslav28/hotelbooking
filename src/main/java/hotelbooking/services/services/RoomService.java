package hotelbooking.services.services;

import hotelbooking.models.dto.RoomDto;
import hotelbooking.models.inputs.RoomInput;

import java.util.List;

public interface RoomService {
    RoomDto createRoom (RoomInput roomInput);
    RoomDto getRoom (Integer id);
    List<RoomDto> getRooms();
    void deleteRoom (Integer id);
    List<RoomDto> getRoomsByHotel (String hotelName);
}
