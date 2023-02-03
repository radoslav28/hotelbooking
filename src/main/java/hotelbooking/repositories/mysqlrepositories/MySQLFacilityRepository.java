package hotelbooking.repositories.mysqlrepositories;

import hotelbooking.models.pojo.Facility;
import hotelbooking.repositories.repositories.FacilityRepository;
import hotelbooking.repositories.rowmappers.FacilityRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MySQLFacilityRepository implements FacilityRepository {

    private final NamedParameterJdbcTemplate namedJdbc;

    public MySQLFacilityRepository(NamedParameterJdbcTemplate namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public Facility createFacility(Facility facility) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String CREATE_FACILITY = "INSERT INTO facilities (facility) " +
                                       "VALUES (:facility) ";

        parameterSource.addValue("facility", facility.getFacility());

        namedJdbc.update(CREATE_FACILITY, parameterSource);

        return facility;
    }

    @Override
    public List<Facility> getFacilities() {

        final String GET_FACILITIES = "SELECT f.facility " +
                                      "FROM facilities f ";
        return namedJdbc.query(GET_FACILITIES, new FacilityRowMapper());
    }

    @Override
    @Transactional
    public void deleteFacility(String facility) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String DELETE_FACILITY_HOTEL = "DELETE " +
                                             "FROM hotels_facilities hf " +
                                             "WHERE facility = :facility ";

        final String DELETE_FACILITY = "DELETE " +
                                       "FROM facilities " +
                                       "WHERE facility = :facility ";

        parameterSource.addValue("facility", facility);

        namedJdbc.update(DELETE_FACILITY_HOTEL, parameterSource);
        namedJdbc.update(DELETE_FACILITY, parameterSource);

    }
}
