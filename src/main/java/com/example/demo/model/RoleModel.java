package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class RoleModel extends BaseModel implements GrantedAuthority {

    private String role_name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<UserRoleModel> userRoles = new HashSet<>();

    @Override
    public String getAuthority() {
        return role_name; // Tên của role chính là quyền hạn
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Set<UserRoleModel> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoleModel> userRoles) {
        this.userRoles = userRoles;
    }
}
