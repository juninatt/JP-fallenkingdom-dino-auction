package se.pbt.dinoauction.model.user.security;

import jakarta.persistence.*;
import se.pbt.dinoauction.model.user.AppUser;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    /**
     * Many-to-Many relationship to {@link AppUser}.
     * This entity is not the owner; the foreign key is managed by the AppUser entity.
     */
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<AppUser> appUsers = new HashSet<>();

    /**
     * Many-to-Many relationship to {@link Permission}.
     * This entity is the owner, managing the 'role_permission' join table.
     */
    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;

    /**
     * No-args constructor for JPA
     */
    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    //Getters and setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AppUser> getUsers() {
        return appUsers;
    }

    public void setUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}

