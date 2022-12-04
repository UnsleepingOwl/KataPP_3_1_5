package ru.unsleepingowl.katasecurity.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "authority")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    private Role(Collection<User> users) {
        this.users = users;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String authority) {
        this.roleName = authority;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return getRoleName().substring(5);
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }
}
