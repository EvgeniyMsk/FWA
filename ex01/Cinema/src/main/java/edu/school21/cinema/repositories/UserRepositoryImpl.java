package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public User getUserByLogin(String login) {
        String SQL = "select * from users where login=?";
        try {
            return jdbcTemplate.queryForObject(SQL, new Object[]{login}, new UserMapper());
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

    @Override
    public void updateUser(User user) {
        try {
            String SQL = "update users set firstname=?, lastname=?, phone=? where id=?";
            jdbcTemplate.execute(SQL, (PreparedStatementCallback<Object>) ps -> {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPhoneNumber());
                ps.setLong(4, user.getId());
                return ps.execute();
            });
        }
        catch (DuplicateKeyException e) {
            System.out.println("Error during updating." + e.getMessage());
        }
    }

    @Override
    public void addSignInInfo(User user, String address) {
        String SQL = "insert into auth(user_id, type, address, time) values (?, ?, ?, ?)";
        jdbcTemplate.execute(SQL, (PreparedStatementCallback<Object>) ps -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            ps.setLong(1, user.getId());
            ps.setString(2, "sign_in");
            ps.setString(3, address);
            ps.setString(4, dateFormat.format(new Date()));
            return ps.execute();
        });
    }

    @Override
    public void addSignUpInfo(User user, String address) {
        String SQL = "insert into auth(user_id, type, address, time) values (?, ?, ?, ?)";
        jdbcTemplate.execute(SQL, (PreparedStatementCallback<Object>) ps -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            ps.setLong(1, user.getId());
            ps.setString(2, "sign_up");
            ps.setString(3, address);
            ps.setString(4, dateFormat.format(new Date()));
            return ps.execute();
        });
    }

    @Override
    public List getAuthInfo(String login) {
        String SQL = "select * from auth where user_id=" + getUserByLogin(login).getId();
        return jdbcTemplate.query(SQL, new AuthHistoryMapper());
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

    private class AuthHistoryMapper implements RowMapper<AuthHistory> {
        @Override
        public AuthHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                AuthHistory authHistory = new AuthHistory();
                authHistory.setUser_id(rs.getLong("user_id"));
                authHistory.setType(rs.getString("type"));
                authHistory.setAddress(rs.getString("address"));
                authHistory.setTime(rs.getString("time"));
                return authHistory;
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
        }
    }

}
