package se.pbt.dinoauction.dataloader;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import se.pbt.dinoauction.factory.UserFactory;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;
import se.pbt.dinoauction.model.entity.user.security.Role;
import se.pbt.dinoauction.repository.user.RoleRepository;
import se.pbt.dinoauction.repository.auctionitem.DinosaurRepository;
import se.pbt.dinoauction.repository.user.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
class DataLoader {
    private final DinosaurRepository dinosaurRepository;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DataLoader(DinosaurRepository dinosaurRepository,
                      UserRepository userRepository,
                      RoleRepository roleRepository) {
        this.dinosaurRepository = dinosaurRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
            loadTestStorage();
            var passWordEncoder = new BCryptPasswordEncoder();
            setTestUser(passWordEncoder);
    }

    private void setTestUser(BCryptPasswordEncoder passWordEncoder) {
        var adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);
        var userFactory = new UserFactory(roleRepository);
        var practiceUser = userFactory.createOrganizer("admin", passWordEncoder.encode("admin"));
        userRepository.save(practiceUser);
    }

    private void loadTestStorage() {
        dinosaurRepository.saveAll(List.of(
                new Dinosaur("Rexinator", "T-Rex with attitude", BigDecimal.valueOf(1_000_000), "Tyrannosaurus", "Male", 5000),
                new Dinosaur("Spike", "Triceratops, great for garden security", BigDecimal.valueOf(400_000), "Triceratops", "Female", 3000),
                new Dinosaur("SkyHigh", "Pterodactyl, it can literally fly", BigDecimal.valueOf(300_000), "Pterodactyl", "Male", 100),
                new Dinosaur("Speedy", "Velociraptor, extremely fast", BigDecimal.valueOf(200_000), "Velociraptor", "Female", 150),
                new Dinosaur("ThunderFoot", "Brachiosaurus, the tall one", BigDecimal.valueOf(700_000), "Brachiosaurus", "Male", 80000),
                new Dinosaur("Swimmer", "Plesiosaur, loves swimming", BigDecimal.valueOf(500_000), "Plesiosaur", "Female", 2000),
                new Dinosaur("Tiny", "Compsognathus, small but dangerous", BigDecimal.valueOf(50_000), "Compsognathus", "Male", 10),
                new Dinosaur("Rocky", "Stegosaurus, wears a natural armor", BigDecimal.valueOf(350_000), "Stegosaurus", "Female", 2500),
                new Dinosaur("King", "Spinosaurus, the aquatic predator", BigDecimal.valueOf(600_000), "Spinosaurus", "Male", 4000),
                new Dinosaur("Blizzard", "Ankylosaurus, the living tank", BigDecimal.valueOf(250_000), "Ankylosaurus", "Female", 2000)
        ));
    }

}
