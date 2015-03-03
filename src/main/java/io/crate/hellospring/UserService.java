package io.crate.hellospring;

import io.crate.action.sql.SQLRequest;
import io.crate.client.CrateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crate.core.CrateTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CrateTemplate crateTemplate;

    @Autowired
    private CrateClient crateClient;

    private Long now() {
        return System.currentTimeMillis();
    }

    public void deleteUsers() {
        userRepository.deleteAll();
    }

    public User createUser(String email, String firstName, String lastName) {
        User user = new User(firstName, lastName, email, now());
        crateTemplate.insert(user);
        return user;
    }

    public void updateUser(User user) {
        crateTemplate.update(user);
    }

    public Collection<User> allUsers() {
        return userRepository.findAll();
    }

    public void refresh() {
        SQLRequest request = new SQLRequest("refresh table users");
        crateClient.sql(request).actionGet();
    }
}

