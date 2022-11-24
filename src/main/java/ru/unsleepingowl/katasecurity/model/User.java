package ru.unsleepingowl.katasecurity.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_security")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="username")
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 2, max = 24, message = "Username should be between 2 and 24 characters")
    private String username;

    @Column(name="password")
    @NotEmpty(message = "Password name should not be empty")
    @Size(min = 8, message = "Password should be at least 8 characters long")
    private String password;


    @Column(name="age")
    @Min(value = 1, message = "Age should be greater than 0")
    private Byte age;

    public User() {
    }

    public User(String username, String lastName, Byte age) {
        this.username = username;
        this.password = lastName;
        this.age = age;
    }
    public User(String username, String lastName, Byte age, Long id) {
        this(username, lastName, age);
        this.id = id;
    }

    @Override
    public String toString() {
        return "[ " +
                "ID = " + id + " | " +
                "Name = '" + username + '\'' + " | " +
                "Age = '" + age + '\''  + " ]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String lastName) {
        this.password = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }
}
