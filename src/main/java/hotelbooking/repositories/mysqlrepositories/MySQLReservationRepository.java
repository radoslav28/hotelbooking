package hotelbooking.repositories.mysqlrepositories;

import hotelbooking.models.pojo.Reservation;
import hotelbooking.repositories.repositories.ReservationRepository;
import hotelbooking.repositories.rowmappers.ReservationRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Repository
public class MySQLReservationRepository implements ReservationRepository {

    private final NamedParameterJdbcTemplate namedJdbc;

    public MySQLReservationRepository(NamedParameterJdbcTemplate namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String CREATE_RESERVATION = "INSERT INTO reservations (room_id, user_id, full_price, start_date, end_date) " +
                                          "VALUES (:roomId, :userId, :fullPrice, :startDate, :endDate) ";

        parameterSource.addValue("roomId", reservation.getRoomId());
        parameterSource.addValue("userId", reservation.getUserId());
        parameterSource.addValue("fullPrice", reservation.getFullPrice());
        parameterSource.addValue("startDate", reservation.getStartDate());
        parameterSource.addValue("endDate", reservation.getEndDate());

        namedJdbc.update(CREATE_RESERVATION, parameterSource, keyHolder);

        Integer id = Objects.requireNonNull(keyHolder.getKey().intValue());
        reservation.setId(id);

        return reservation;
    }

    @Override
    public Reservation getReservation(Integer id) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String GET_RESERVATION = "SELECT  r.id, r.room_id, r.user_id, r.full_price, r.start_date,  r.end_date " +
                                       "FROM reservations r " +
                                       "WHERE r.id = :id ";

        parameterSource.addValue("id", id);

        Reservation reservation = namedJdbc.query(GET_RESERVATION, parameterSource, new ReservationRowMapper())
                .stream()
                .findFirst()
                .orElse(null);

        return reservation;
    }

    @Override
    public List<Reservation> getReservations() {

        final String GET_RESERVATION = "SELECT  r.id, r.room_id, r.user_id, r.full_price, r.start_date,  r.end_date " +
                                       "FROM reservations r ";


        return namedJdbc.query(GET_RESERVATION, new ReservationRowMapper());
    }

    @Override
    public void deleteReservation(Integer id) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        String DELETE_RESERVATION = "DELETE " +
                                    "FROM reservations r " +
                                    "WHERE r.id = :id ";

        parameterSource.addValue("id", id);

        namedJdbc.update(DELETE_RESERVATION, parameterSource);

    }
}
