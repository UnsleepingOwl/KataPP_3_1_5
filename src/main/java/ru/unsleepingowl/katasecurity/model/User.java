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


    @Column(name="name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @Column(name="last_name")
    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 100 characters")
    private String lastName;


    @Column(name="age")
    @Min(value = 1, message = "Age should be greater than 0")
    private Byte age;

    public User() {
    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }
    public User(String name, String lastName, Byte age, Long id) {
        this(name, lastName, age);
        this.id = id;
    }

    @Override
    public String toString() {
        return "[ " +
                "ID = " + id + " | " +
                "Name = '" + name + '\'' + " | " +
                "Last Name = '" + lastName + '\'' + " | " +
                "Age = '" + age + '\''  + " ]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }
}
