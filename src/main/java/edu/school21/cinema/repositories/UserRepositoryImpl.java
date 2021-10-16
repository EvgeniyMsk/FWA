package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.slf4j.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    public static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUserById(Long id) {
        String SQL = "select * from users where id=?";
        try {
            return jdbcTemplate.queryForObject(SQL, new Object[]{id}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getUserByLogin(String firstName) {
        String SQL = "select * from users where login=?";
        try {
            return jdbcTemplate.queryForObject(SQL, new Object[]{firstName}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void saveUser(User user) {
        try {
            String SQL = "insert into users(login, password, firstname, lastname, phone) values (?, ?, ?, ?, ?)";
            jdbcTemplate.execute(SQL, (PreparedStatementCallback<Object>) ps -> {
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFirstName());
                ps.setString(4, user.getLastName());
                ps.setString(5, user.getPhoneNumber());
                return ps.execute();
            });
        }
        catch (DuplicateKeyException e) {
            System.out.println("Error during registration." + e.getMessage());
        }
    }

    private class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setPhoneNumber(rs.getString("phone"));
                return user;
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
        }

    }
}
