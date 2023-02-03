package hotelbooking.repositories.mysqlrepositories;

import hotelbooking.models.pojo.Room;
import hotelbooking.repositories.repositories.RoomRepository;
import hotelbooking.repositories.rowmappers.RoomRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class MySQLRoomRepository implements RoomRepository {

    private final NamedParameterJdbcTemplate namedJdbc;

    public MySQLRoomRepository(NamedParameterJdbcTemplate namedJdbc) {
        this.namedJdbc = namedJdbc;
    }


    @Override
    public Room createRoom(Room room) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String CREATE_ROOM = "INSERT INTO rooms (hotel_id, capacity, price) " +
                                   "VALUES (:hotelId, :capacity, :price) ";

        parameterSource.addValue("hotelId", room.getHotelId());
        parameterSource.addValue("capacity", room.getCapacity());
        parameterSource.addValue("price", room.getPrice());

        namedJdbc.update(CREATE_ROOM, parameterSource, keyHolder);

        Integer id = Objects.requireNonNull(keyHolder.getKey().intValue());
        room.setId(id);

        return room;
    }

    @Override
    public Room getRoom(Integer id) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String GET_ROOM = "SELECT r.id, r.hotel_id, r.capacity, r.price " +
                                "FROM rooms r " +
                                "WHERE r.id = :id ";

        parameterSource.addValue("id", id);

        Room room = namedJdbc.query(GET_ROOM, parameterSource, new RoomRowMapper())
                .stream()
                .findFirst()
                .orElse(null);

        return room;
    }

    @Override
    public List<Room> getRooms() {

        final String GET_ROOMS = "SELECT r.id, r.hotel_id, r.capacity, r.price " +
                                 "FROM rooms r ";

        return namedJdbc.query(GET_ROOMS, new RoomRowMapper());
    }

    @Override
    public void deleteRoom(Integer id) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String DELETE_ROOM = "DELETE " +
                                   "FROM rooms r " +
                                   "WHERE r.id = :id ";

        parameterSource.addValue("id", id);

        namedJdbc.update(DELETE_ROOM, parameterSource);
    }

    @Override
    public List<Room> getRoomsByHotel(List<Integer> hotelIds) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();


        final String GET_ROOMS = "SELECT r.id, r.hotel_id, r.capacity, r.price " +
                                 "FROM rooms r " +
                                 "WHERE r.hotel_id IN (:hotels) ";

        parameterSource.addValue("hotels", hotelIds);

        return namedJdbc.query(GET_ROOMS, parameterSource, new RoomRowMapper());
    }
}
