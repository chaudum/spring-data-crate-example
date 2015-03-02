package io.crate.hellospring;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.crate.core.mapping.CratePersistentEntity;
import org.springframework.data.crate.core.mapping.SimpleCratePersistentEntity;
import org.springframework.data.crate.core.mapping.annotations.Column;
import org.springframework.data.crate.core.mapping.annotations.Table;


@Table(name="users")
public class User {

    @Id
    @Email
    @NotBlank
    private String email;

    private String firstName;
    private String lastName;
    private Integer age;


    public User(String firstName, String lastName, String email, Integer age) {
        this(firstName, lastName, email, age, new String[]{}, new Object());
    }

    public User(String firstName, String lastName, String email, Integer age, String[] tags, Object attributes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
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

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return String.format(
                "User[firstName='%s', lastName='%s']", firstName, lastName);
    }

}

