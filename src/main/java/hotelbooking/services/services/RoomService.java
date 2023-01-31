package hotelbooking.services.services;

import hotelbooking.models.dto.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto createRoom (String hotelName, Integer capacity, Double price);
    RoomDto getRoom (Integer id);
    List<RoomDto> getRooms();
    void deleteRoom (Integer id);
    List<RoomDto> getRoomsByHotel (String hotelName);
}
