package hotelbooking.repositories.rowmappers;

import hotelbooking.models.pojo.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper<Reservation> {
    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("id"));
        reservation.setRoomId(rs.getInt("room_id"));
        reservation.setUserId(rs.getInt("user_id"));
        reservation.setStartDate(rs.getDate("start_date"));
        reservation.setEndDate(rs.getDate("end_date"));
        reservation.setFullPrice(rs.getDouble("full_price"));
        return reservation;
    }
}
