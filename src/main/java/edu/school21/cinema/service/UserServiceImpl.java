package edu.school21.cinema.service;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean signUp(String login, String password, String address) throws SQLException {
        if (!login.isEmpty() && !password.isEmpty())
            if (userRepository.getUserByLogin(login) == null)
            {
                User user = new User(login, password, "", "", "");
                userRepository.saveUser(user);
                userRepository.addSignUpInfo(userRepository.getUserByLogin(login), address);
                return true;
            }
        return false;
    }

    @Override
    public boolean signIn(String login, String password, String address) {
        if (!login.isEmpty() && !password.isEmpty())
        {
            User tempUser = userRepository.getUserByLogin(login);
            if ((tempUser != null) && tempUser.getPassword().equals(password))
            {
                userRepository.addSignInInfo(userRepository.getUserByLogin(login), address);
                return true;
            }
        }
        return false;
    }

    @Override
    public User getProfile(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public void updateProfile(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public List<AuthHistory> getAuth(String login) {
        return userRepository.getAuthInfo(login);
    }
}
