package hotelbooking.repositories.rowmappers;

import hotelbooking.models.pojo.Hotel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelRowMapper implements RowMapper<Hotel> {
    @Override
    public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("id"));
        hotel.setHotelName(rs.getString("hotel_name"));
        hotel.setCityId(rs.getInt("city_id"));
        return hotel;
    }
}
