package edu.school21.cinema.service;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean signUp(String login, String password) throws SQLException {
        if (!login.isEmpty() && !password.isEmpty())
            if (userRepository.getUserByLogin(login) == null)
            {
                userRepository.saveUser(new User(login, password, "", "", ""));
                return true;
            }
        return false;
    }

    @Override
    public boolean signIn(String login, String password) {
        if (!login.isEmpty() && !password.isEmpty())
        {
            User tempUser = userRepository.getUserByLogin(login);
            return (tempUser != null) && tempUser.getPassword().equals(password);
        }
        return false;
    }

    @Override
    public void logOut() {

    }
}
