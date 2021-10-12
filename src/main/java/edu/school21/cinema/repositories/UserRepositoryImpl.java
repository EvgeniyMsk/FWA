package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
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
    public Optional<User> getUserByFirstName(String firstName) {
        return Optional.empty();
    }

    @Override
    public void saveUser(User user) {
        jdbcTemplate.execute("insert into users(firstname, password) " +
                    "values ('" + user.getFirstName() + "', '" + user.getPassword() + "')");
    }

    private class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                User user = new User();
                user.setId(rs.getLong("id"));
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
