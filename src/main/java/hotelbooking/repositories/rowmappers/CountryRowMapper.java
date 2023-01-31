package hotelbooking.repositories.rowmappers;

import hotelbooking.models.pojo.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        Country country = new Country();
        country.setCountry(rs.getString("country"));
        return country;
    }
}
