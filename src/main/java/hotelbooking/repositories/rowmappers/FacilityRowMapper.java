package hotelbooking.repositories.rowmappers;

import hotelbooking.models.pojo.Facility;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacilityRowMapper implements RowMapper<Facility> {
    @Override
    public Facility mapRow(ResultSet rs, int rowNum) throws SQLException {
        Facility facility = new Facility();
        facility.setFacility(rs.getString("facility"));
        return facility;
    }
}
