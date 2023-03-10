package hotelbooking.repositories.repositories;

import hotelbooking.models.pojo.Room;

import java.util.List;

public interface RoomRepository {
    Room createRoom(Room room);
    Room getRoom(Integer id);
    List<Room> getRooms();
    void deleteRoom(Integer id);
    List<Room> getRoomsByHotel(List<Integer> hotelIds);
}
