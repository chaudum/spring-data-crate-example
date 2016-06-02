package io.crate.hellospring;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.crate.core.mapping.annotations.Table;

import java.util.HashMap;


@Table(name="users", refreshInterval=500, numberOfReplicas="0-all")
public class User {

    @Id
    @Email
    @NotBlank
    private String email;
    private String firstName;
    private String lastName;
    private Long dateJoined;
    private String[] tags;
    private HashMap<String, Object> attributes;

    @PersistenceConstructor
    public User(String firstName, String lastName, String email, Long dateJoined) {
        this(firstName, lastName, email, dateJoined, new String[]{});
    }

    @PersistenceConstructor
    public User(String firstName, String lastName, String email, Long dateJoined, String[] tags) {
        this(firstName, lastName, email, dateJoined, tags, new HashMap<>());
    }

    @PersistenceConstructor
    public User(String firstName, String lastName, String email, Long dateJoined, String[] tags, HashMap<String, Object> attributes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateJoined = dateJoined;
        this.tags = tags;
        this.attributes = attributes;
    }

    public String getId() {
        return this.email;
    }
    public void setId(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
    public void setFullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDateJoined() {
        return dateJoined;
    }
    public void setDateJoined(Long dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String[] getTags() {
        return tags;
    }
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }
    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return String.format(
                "User[firstName='%s', lastName='%s']", firstName, lastName);
    }

}

