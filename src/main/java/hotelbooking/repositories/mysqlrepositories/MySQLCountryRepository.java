package hotelbooking.repositories.mysqlrepositories;

import hotelbooking.models.pojo.Country;
import hotelbooking.repositories.repositories.CountryRepository;
import hotelbooking.repositories.rowmappers.CountryRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLCountryRepository implements CountryRepository {
    private final NamedParameterJdbcTemplate namedJdbc;

    public MySQLCountryRepository(NamedParameterJdbcTemplate namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public Country createCountry(String country) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String CREATE_COUNTRY = "INSERT INTO countries (country) " +
                                      "VALUES (:country) ";

        parameterSource.addValue("country", country);

        namedJdbc.update(CREATE_COUNTRY, parameterSource);

        return new Country(country);
    }

    @Override
    public List<Country> getCountries() {

        final String GET_COUNTRIES = "SELECT c.country " +
                                     "FROM countries c ";


        return namedJdbc.query(GET_COUNTRIES, new CountryRowMapper());
    }

    @Override
    public void deleteCountry(String country) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String DELETE_COUNTRY = "DELETE " +
                                      "FROM countries " +
                                      "WHERE country = :country ";

        parameterSource.addValue("country", country);

        namedJdbc.update(DELETE_COUNTRY, parameterSource);
    }
}
