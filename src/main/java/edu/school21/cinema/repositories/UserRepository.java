package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import java.util.Optional;

public interface UserRepository {
    User getUserById(Long id);
    Optional<User> getUserByFirstName(String firstName);
    void saveUser(User user);
}
