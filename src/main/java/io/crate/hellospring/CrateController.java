package io.crate.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
public class CrateController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public Collection<User> index() {
        // reset
        userService.deleteUsers();

        // create users
        User christian = userService.createUser("christian.haudum@example.com", "Christian", "Haudum");
        User rizwan = userService.createUser("rizwan.idrees@example.com", "Rizwan", "Idrees");
        User hasnain = userService.createUser("hasnain.javed@example.com", "Hasnain", "Javed");

        // update users
        christian.setTags(new String[]{"crateio","python"});
        christian.setAttributes(new HashMap<String, Object>(){{ put("company","Crate.IO"); }});
        userService.updateUser(christian);

        rizwan.setTags(new String[]{"kptechnologylab","java","spring framework"});
        rizwan.setAttributes(new HashMap<String, Object>(){{ put("company", "KP Technology Lab"); }});
        userService.updateUser(rizwan);

        hasnain.setTags(new String[]{"kptechnologylab","java","spring data"});
        hasnain.setAttributes(new HashMap<String, Object>(){{ put("company", "KP Technology Lab"); }});
        userService.updateUser(hasnain);

        // refresh table to make sure we get latest version of the documents
        userService.refresh();

        System.out.println("======================");
        for (User user : userService.allUsers()) {
            System.out.println(String.format("Email        %s", user.getEmail()));
            System.out.println(String.format("Name         %s", user.getFullName()));
            System.out.println(String.format("Joined       %s", user.getDateJoined().toString()));
            System.out.println("Tags");
            for (String tag : user.getTags()) {
                System.out.println(String.format("   %s", tag));
            }
            System.out.println("Attributes");
            for (Map.Entry<String, Object> map : user.getAttributes().entrySet()) {
                System.out.println(String.format("   %s : %s", map.getKey(), map.getValue().toString()));
            }
            System.out.println("======================");
        }

        return userService.allUsers();
    }
}
