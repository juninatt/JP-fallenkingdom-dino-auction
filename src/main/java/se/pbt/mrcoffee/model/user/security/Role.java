package se.pbt.mrcoffee.model.user.security;

import jakarta.persistence.*;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<MrCoffeeUser> users = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;



    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<MrCoffeeUser> getUsers() {
        return users;
    }

    public void setUsers(Set<MrCoffeeUser> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}

