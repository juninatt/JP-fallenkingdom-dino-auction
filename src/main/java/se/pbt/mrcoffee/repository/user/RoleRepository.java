package se.pbt.mrcoffee.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import se.pbt.mrcoffee.model.user.security.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String roleName);
}
