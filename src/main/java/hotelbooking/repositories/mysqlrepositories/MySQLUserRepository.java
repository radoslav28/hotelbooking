package hotelbooking.repositories.mysqlrepositories;

import hotelbooking.models.pojo.User;
import hotelbooking.repositories.repositories.UserRepository;
import hotelbooking.repositories.rowmappers.UserAuthRowMapper;
import hotelbooking.repositories.rowmappers.UserRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class MySQLUserRepository implements UserRepository {

    private final NamedParameterJdbcTemplate namedJdbc;

    public MySQLUserRepository(NamedParameterJdbcTemplate namedJdbc) {
        this.namedJdbc = namedJdbc;
    }


    @Override
    public User createUser(User user) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String CREATE_USER = "INSERT INTO users (email, password, first_name, last_name, phone) " +
                                   "VALUES (:email, :password, :firstName, :lastName, :phone) ";

        parameterSource.addValue("email", user.getEmail());
        parameterSource.addValue("password", user.getPassword());
        parameterSource.addValue("firstName", user.getFirstName());
        parameterSource.addValue("lastName", user.getLastName());
        parameterSource.addValue("phone", user.getPhone());

        namedJdbc.update(CREATE_USER, parameterSource, keyHolder);

        Integer id = Objects.requireNonNull(keyHolder.getKey().intValue());
        user.setId(id);

        return user;
    }

    @Override
    public User getUser(Integer id) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String GET_USER = "SELECT u.id, u.email, u.password, u.first_name, u.last_name, u.phone " +
                                "FROM users as u " +
                                "WHERE u.id = :id ";


        parameterSource.addValue("id", id);

        User user = namedJdbc.query(GET_USER, parameterSource, new UserRowMapper())
                .stream()
                .findFirst()
                .orElse(null);

        return user;
    }

    @Override
    public List<User> getUsers() {

        final String GET_USERS = "SELECT u.id, u.email, u.password, u.first_name, u.last_name, u.phone " +
                                 "FROM users u ";

        return namedJdbc.query(GET_USERS, new UserRowMapper());
    }

    @Override
    public void deleteUser(Integer id) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        final String DELETE_USER = "DELETE " +
                                   "FROM users u " +
                                   "WHERE u.id = :id ";

        parameterSource.addValue("id", id);

        namedJdbc.update(DELETE_USER, parameterSource);
    }

    @Override
    public User getUser(String email) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue("email", email);

        final String GET_USER = "SELECT u.id, u.email, u.password, u.first_name, u.role, u.last_name, u.phone " +
                                "FROM users u " +
                                "WHERE email = :email ";

        User user = namedJdbc.query(GET_USER, parameterSource, new UserAuthRowMapper())
                .stream()
                .findFirst()
                .orElse(null);

        return user;
    }
}
