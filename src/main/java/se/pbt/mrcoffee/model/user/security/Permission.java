package se.pbt.mrcoffee.model.user.security;

import jakarta.persistence.*;

import java.util.Set;

/**
 * Entity class representing a permission.
 * A permission is used to define granular access controls and is associated with roles.
 */
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Column(unique = true)
    private String permissionName;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;


    /**
     * Adds a role to this permission and this permission to the role.
     *
     * @param role The role to be added.
     */
    public void addRole(Role role) {
        this.roles.add(role);
        role.getPermissions().add(this);
    }

    /**
     * Removes a role from this permission and this permission from the role.
     *
     * @param role The role to be removed.
     */
    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getPermissions().remove(this);
    }

    // Getters and setters

    public Long getPermissionId() {
        return permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
