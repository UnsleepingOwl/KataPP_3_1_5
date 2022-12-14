package ru.unsleepingowl.katasecurity.DTO;

import ru.unsleepingowl.katasecurity.model.Role;
import ru.unsleepingowl.katasecurity.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserDTO {

    private Long id;

    @Email(message = "Email should be valid")
    @Size(min = 2, max = 24, message = "Username should be between 2 and 24 characters")
    private String username;

    @NotEmpty(message = "First name should not be empty")
    @Size(min = 2, max = 40, message = "Username should be between 2 and 30 characters")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 40, message = "Username should be between 2 and 40 characters")
    private String lastName;

    private String password;

    @Min(value = 1, message = "Age should be greater than 0")
    private Byte age;

    private Set<Role> roles;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.roles = user.getRoles();
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

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }
}
