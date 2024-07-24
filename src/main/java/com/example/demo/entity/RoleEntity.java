package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity implements GrantedAuthority {

    private String role_name;

    @JsonManagedReference
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserRoleEntity> userRoles = new HashSet<>();

    @Override
    public String getAuthority() {
        return role_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Set<UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }
}
