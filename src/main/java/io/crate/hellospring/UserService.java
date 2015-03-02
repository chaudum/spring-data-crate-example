package io.crate.hellospring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = null;
    }

    public UserService(UserRepository userRepository) {
       this.userRepository = userRepository;
    }

    public void doStuff(){

        userRepository.deleteAll();

        User christian = new User("Christian", "Haudum", "christian.haudum@crate.io", 0,
                new String[]{"javascript"}, new Object());
        userRepository.save(christian);

        User bernd = new User("Bernd", "Dorn", "bernd@crate.io", 0,
                new String[]{"python", "cto"}, new Object());
        userRepository.save(bernd);

        User jordi = new User("Mathias", "Fussenegger", "mathias@crate.io", 0,
                new String[]{"java"}, new Object());
        userRepository.save(jordi);

//        User dbUser = userRepository.findById(user.getId());
//        List<User> users = userRepository.findAll();

    }

}

