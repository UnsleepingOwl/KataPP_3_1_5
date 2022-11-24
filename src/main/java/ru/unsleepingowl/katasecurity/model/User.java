package ru.unsleepingowl.katasecurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "user_security")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "username")
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 2, max = 24, message = "Username should be between 2 and 24 characters")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Password name should not be empty")
    @Size(min = 8, message = "Password should be at least 8 characters long")
    private String password;


    @Column(name = "age")
    @Min(value = 1, message = "Age should be greater than 0")
    private Byte age;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "role_id")
//            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Collection<Role> roles;

    public User() {
    }

    public User(String username, String lastName, Byte age) {
        this.username = username;
        this.password = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "[ " +
                "ID = " + id + " | " +
                "Name = '" + username + '\'' + " | " +
                "Age = '" + age + '\'' + " ]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
