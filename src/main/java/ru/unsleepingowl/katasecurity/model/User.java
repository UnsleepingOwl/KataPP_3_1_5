package ru.unsleepingowl.katasecurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "bootstrap_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    @Email(message = "Email should be valid")
    @Size(min = 2, max = 24, message = "Username should be between 2 and 24 characters")
    private String username;

    @Column(name = "first_name")
    @NotEmpty(message = "First name should not be empty")
    @Size(min = 2, max = 40, message = "Username should be between 2 and 30 characters")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Column(name = "last_name")
    @Size(min = 2, max = 40, message = "Username should be between 2 and 40 characters")
    private String lastName;

    @Column(name = "password")
    @NotEmpty(message = "Password name should not be empty")
    @Size(min = 8, message = "Password should be at least 8 characters long")
    private String password;

    @Column(name = "age")
    @Min(value = 1, message = "Age should be greater than 0")
    private Byte age;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bootstrap_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String email, String password, String firstName, String lastName, Byte age) {
        this.username = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(String email, String password, String firstName, String lastName, Byte age, Collection<Role> roles) {
        this(email, password, firstName, lastName, age);
        this.roles = (Set<Role>) roles;
    }

    @Override
    public String toString() {
        return "[ " +
                "ID = " + id + " | " +
                "Email = '" + username + '\'' + " | " +
                "First Name = '" + firstName + '\'' + " | " +
                "Last Name = '" + lastName + '\'' + " | " +
                "Age = '" + age + '\'' + " ]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
    }

    public String rolesToString() {
        return roles.stream().map(Role::toString).collect(Collectors.joining(" "));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = (Set<Role>) roles;
    }
}
