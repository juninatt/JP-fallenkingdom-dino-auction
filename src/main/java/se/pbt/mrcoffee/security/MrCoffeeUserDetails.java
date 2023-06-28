package se.pbt.mrcoffee.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;
import se.pbt.mrcoffee.model.user.security.Role;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the user details for a MrCoffeeUser. This class is an implementation of the UserDetails interface
 * provided by Spring Security.
 */
public class MrCoffeeUserDetails implements UserDetails {

    private final MrCoffeeUser user;

    /**
     * Constructs a new MrCoffeeUserDetails instance with the provided MrCoffeeUser object.
     *
     * @param user the MrCoffeeUser object representing the user details
     */
    public MrCoffeeUserDetails(MrCoffeeUser user) {
        this.user = user;
    }

    /**
     * Returns the authorities granted to the user.
     *
     * @return a collection of GrantedAuthority objects representing the user's authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        // Iterate over the user's roles and add them as authorities
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authorities;
    }



    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
