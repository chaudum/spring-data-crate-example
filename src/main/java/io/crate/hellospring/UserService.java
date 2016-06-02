package io.crate.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private Long now() {
        return System.currentTimeMillis();
    }

    public void deleteUsers() {
        userRepository.deleteAll();
    }

    public User createUser(String email, String firstName, String lastName) {
        return userRepository.save(new User(firstName, lastName, email, now()));
    }

    public void updateUser(User user) {
        // userRepository.save is clever enough to distinguish between insert and update
        userRepository.save(user);
    }

    public Collection<User> allUsers() {
        return userRepository.findAll();
    }

    public void refresh() {
        userRepository.refreshTable();
    }
}
