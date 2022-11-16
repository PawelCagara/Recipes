package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.model.User;
import recipes.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return new User(user.getEmail(), user.getPassword());
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User checkIfEmailIsInDatabase(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }
}
