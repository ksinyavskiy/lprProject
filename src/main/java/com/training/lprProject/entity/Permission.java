package com.training.lprProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PERMISSION")
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERMISSION_ID", unique = true, nullable = false)
    private Long permissionId;
    @Column(name = "NAME", length = 20, unique = true, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private Set<Role> roles;

    public Permission() {

    }

    public Long getPermissionId() {
        return permissionId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (this.getClass() != object.getClass()) {
            return false;
        }
        Permission permission = (Permission) object;

        return Objects.equals(permissionId, permission.getPermissionId()) &&
                Objects.equals(name, permission.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, name);
    }

    @Override
    public String toString() {
        return String.format("Permission: id = %d, name = %s", permissionId, name);
    }
}
