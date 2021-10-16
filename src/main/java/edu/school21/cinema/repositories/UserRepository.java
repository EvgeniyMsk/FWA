package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserRepository {
    User getUserById(Long id);
    User getUserByLogin(String login);
    void saveUser(User user) throws SQLException;
}
