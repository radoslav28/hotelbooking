package hotelbooking.repositories.mysqlrepositories;

import hotelbooking.models.pojo.Hotel;
import hotelbooking.repositories.repositories.HotelRepository;
import hotelbooking.repositories.rowmappers.HotelRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class MySQLHotelRepository implements HotelRepository {

    private final NamedParameterJdbcTemplate namedJdbc;

    public MySQLHotelRepository(NamedParameterJdbcTemplate namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public Hotel createHotel(Hotel hotel) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String CREATE_HOTEL = "INSERT INTO hotels (hotel_name, city_id) " +
                                    "VALUES (:hotelName, :cityId) ";

        parameterSource.addValue("hotelName", hotel.getHotelName());
        parameterSource.addValue("cityId", hotel.getCityId());

        namedJdbc.update(CREATE_HOTEL, parameterSource, keyHolder);

        Integer id = Objects.requireNonNull(keyHolder.getKey().intValue());
        hotel.setId(id);

        return hotel;
    }

    @Override
    public Hotel getHotel(Integer id) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String GET_HOTEl = "SELECT h.id, h.hotel_name, h.city_id " +
                              "FROM hotels h " +
                              "WHERE h.id = :id ";

        parameterSource.addValue("id", id);

        Hotel hotel = namedJdbc.query(GET_HOTEl, parameterSource, new HotelRowMapper())
                .stream()
                .findFirst()
                .orElse(null);

        return hotel;
    }

    @Override
    public Hotel getHotel(String hotelName) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String GET_HOTEl = "SELECT h.id, h.hotel_name, h.city_id " +
                "FROM hotels h " +
                "WHERE h.hotel_name = :hotelName ";

        parameterSource.addValue("hotelName", hotelName);

        Hotel hotel = namedJdbc.query(GET_HOTEl, parameterSource, new HotelRowMapper())
                .stream()
                .findFirst()
                .orElse(null);

        return hotel;
    }

    @Override
    public List<Hotel> getHotels() {

        final String GET_HOTELS = "SELECT h.id, h.hotel_name, h.city_id " +
                                  "FROM hotels h ";

        return namedJdbc.query(GET_HOTELS, new HotelRowMapper());
    }

    @Override
    public void deleteHotel(Integer id) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        String DELETE_HOTEL = "DELETE " +
                              "FROM hotels h " +
                              "WHERE h.id = :id ";

        parameterSource.addValue("id", id);

        namedJdbc.update(DELETE_HOTEL, parameterSource);

    }

    @Override
    public List<Hotel> getHotelsByCity(List<Integer> cityIds) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String GET_HOTELS = "SELECT h.id, h.hotel_name, h.city_id " +
                                  "FROM hotels h " +
                                  "WHERE h.city_id IN (:cities) ";

        parameterSource.addValue("cities", cityIds);

        return namedJdbc.query(GET_HOTELS, parameterSource, new HotelRowMapper());
    }


    @Override
    public void giveHotelFacility(Integer hotelId, String facility) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String GIVE_HOTEL_FACILITY = "INSERT INTO hotels_facilities (hotel_id, facility) " +
                                           "VALUES (:hotelId, :facility) ";

        parameterSource.addValue("hotelId", hotelId);
        parameterSource.addValue("facility", facility);

        namedJdbc.update(GIVE_HOTEL_FACILITY, parameterSource);
    }

    @Override
    public List<Hotel> getHotelsByFacility(List<String> facilities) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String GET_HOTELS = "SELECT h.id, h.hotel_name, h.city_id " +
                "FROM hotels h " +
                "JOIN hotels_facilities hf ON h.id = hf.hotel_id " +
                "WHERE hf.facility IN (:facilities) ";

        parameterSource.addValue("facilities", facilities);

        return namedJdbc.query(GET_HOTELS, parameterSource, new HotelRowMapper());
    }

}
