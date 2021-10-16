package edu.school21.cinema.service;

import java.sql.SQLException;

public interface UserService {
    boolean signUp(String login, String password) throws SQLException;
    boolean signIn(String login, String password);
    void logOut();
}
