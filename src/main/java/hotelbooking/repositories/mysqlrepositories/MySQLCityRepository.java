package hotelbooking.repositories.mysqlrepositories;

import hotelbooking.models.pojo.City;
import hotelbooking.repositories.repositories.CityRepository;
import hotelbooking.repositories.rowmappers.CityRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class MySQLCityRepository implements CityRepository {

    private final NamedParameterJdbcTemplate namedJdbc;

    public MySQLCityRepository(NamedParameterJdbcTemplate namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public City createCity(String cityName, String countryName) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String CREATE_CITY = "INSERT INTO cities (city_name, country_name) " +
                                   "VALUES (:city, :country) ";

        parameterSource.addValue("city", cityName);
        parameterSource.addValue("country", countryName);

        namedJdbc.update(CREATE_CITY, parameterSource, keyHolder);

        Integer id = Objects.requireNonNull(keyHolder.getKey().intValue());

        return new City(id, cityName, countryName);
    }

    @Override
    public City getCity(Integer id) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        String GET_CITY = "SELECT c.id, c.city_name, c.country_name " +
                        "FROM cities c " +
                        "WHERE c.id = :id ";

        parameterSource.addValue("id", id);

        City city = namedJdbc.query(GET_CITY, parameterSource, new CityRowMapper())
                .stream()
                .findFirst()
                .orElse(null);

        return city;
    }

    @Override
    public City getCity(String cityName) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        String GET_CITY = "SELECT c.id, c.city_name, c.country_name " +
                "FROM cities c " +
                "WHERE c.city_name = :cityName ";

        parameterSource.addValue("cityName", cityName);

        City city = namedJdbc.query(GET_CITY, parameterSource, new CityRowMapper())
                .stream()
                .findFirst()
                .orElse(null);

        return city;
    }

    @Override
    public List<City> getCities() {

        String GET_CITIES = "SELECT c.id, c.city_name, c.country_name " +
                            "FROM cities c ";

        return namedJdbc.query(GET_CITIES, new CityRowMapper());
    }

    @Override
    public List<City> getCities(List<String> cities) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        String GET_CITIES = "SELECT c.id, c.city_name, c.country_name " +
                            "FROM cities c " +
                            "WHERE c.city_name IN (:cities) ";

        parameterSource.addValue("cities", cities);

        return namedJdbc.query(GET_CITIES, parameterSource, new CityRowMapper());
    }

    @Override
    public List<City> getCitiesByCountry(List<String> countries) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        String GET_CITIES = "SELECT c.id, c.city_name, c.country_name " +
                            "FROM cities c " +
                            "WHERE c.country_name IN :countries ";

        parameterSource.addValue("countries", countries);

        return namedJdbc.query(GET_CITIES, parameterSource, new CityRowMapper());
    }

    @Override
    public void deleteCity(Integer id) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String DELETE_CITY = "DELETE FROM cities " +
                                   "WHERE id = :id ";

        parameterSource.addValue("id", id);

        namedJdbc.update(DELETE_CITY, parameterSource);
    }

    @Override
    public void deleteCities(String country) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String DELETE_CITIES = "DELETE FROM cities " +
                                     "WHERE country_name = :country ";

        parameterSource.addValue("country", country);

        namedJdbc.update(DELETE_CITIES, parameterSource);

    }
}
