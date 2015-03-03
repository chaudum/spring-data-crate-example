package io.crate.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class CrateApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;


    public CrateApplication() {}

	public static void main(String[] args) {
        SpringApplication.run(CrateApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        // reset
        userService.deleteUsers();

        // create users
        User christian = userService.createUser("christian.haudum@example.com", "Christian", "Haudum");
        User rizwan = userService.createUser("rizwan.idrees@example.com", "Rizwan", "Idrees");
        User hasnain = userService.createUser("hasnain.javed@example.com", "Hasnain", "Javed");

        // refresh table to make sure we get latest version of the documents
        userService.refresh();

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
            System.out.println("--------------------------");
        }
        System.out.println("======================");

    }

}
