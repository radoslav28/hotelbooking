package hotelbooking.repositories.rowmappers;

import hotelbooking.models.pojo.City;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRowMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        City city = new City();
        city.setId(rs.getInt("id"));
        city.setCityName(rs.getString("city_name"));
        city.setCountryName(rs.getString("country_name"));
        return city;
    }
}
