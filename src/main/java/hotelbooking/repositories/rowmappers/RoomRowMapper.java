package hotelbooking.repositories.rowmappers;

import hotelbooking.models.pojo.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRowMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("id"));
        room.setHotelId(rs.getInt("hotel_id"));
        room.setCapacity(rs.getInt("capacity"));
        room.setPrice(rs.getDouble("price"));
        return room;
    }
}
