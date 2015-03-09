package io.crate.hellospring;

import io.crate.action.sql.SQLRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crate.core.CrateAction;
import org.springframework.data.crate.core.CrateTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserService {

    private static final class RefreshUserTableAction implements CrateAction {
        @Override
        public SQLRequest getSQLRequest() {
            return new SQLRequest(getSQLStatement());
        }
        @Override
        public String getSQLStatement() {
            return "refresh table users";
        }
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CrateTemplate crateTemplate;

    private Long now() {
        return System.currentTimeMillis();
    }

    public void deleteUsers() {
        userRepository.deleteAll();
    }

    public User createUser(String email, String firstName, String lastName) {
        User user = new User(firstName, lastName, email, now());
        userRepository.save(user);
        return user;
    }

    public void updateUser(User user) {
        // userRepository.save is clever enough to distinguish between insert and update
        userRepository.save(user);
    }

    public Collection<User> allUsers() {
        return userRepository.findAll();
    }

    public void refresh() {
        crateTemplate.execute(new RefreshUserTableAction());
    }
}

