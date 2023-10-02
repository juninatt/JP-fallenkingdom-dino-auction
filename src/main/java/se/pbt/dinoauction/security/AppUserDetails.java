package se.pbt.dinoauction.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.pbt.dinoauction.model.user.AppUser;
import se.pbt.dinoauction.model.user.security.Role;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link UserDetails} to represent the details of an {@link AppUser}.
 * This class is tailored to integrate with Spring Security, encapsulating the security-related
 * attributes and behaviors for an application user.
 */
public class AppUserDetails implements UserDetails {

    private final AppUser appUser;

    /**
     * Initializes an instance of {@code AppUserDetails} using the given {@link AppUser}.
     *
     * @param appUser The user data model, typically retrieved from the database.
     */
    public AppUserDetails(AppUser appUser) {
        this.appUser = appUser;
    }

    /**
     * Constructs and returns the list of authorities (roles) granted to the user.
     * Implements the method from the {@link UserDetails} interface.
     *
     * @return A collection of {@link GrantedAuthority}, each representing a user role.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        // Iterate over the user's roles and add them as authorities
        for (Role role : appUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
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
}
