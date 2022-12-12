package ru.unsleepingowl.katasecurity.DTO;

import org.springframework.security.core.GrantedAuthority;
import ru.unsleepingowl.katasecurity.model.Role;

import javax.persistence.*;
import java.util.Objects;

public class RoleDTO {

    private Long id;

    private String authority;

    public RoleDTO() {
    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO role = (RoleDTO) o;
        return id.equals(role.id) && authority.equals(role.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }

    @Override
    public String toString() {
        return getAuthority();
    }
}
