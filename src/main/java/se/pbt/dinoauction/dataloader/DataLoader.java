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
        var PX = new Dinosaur("PixieSaurus", "Meet the Juvenile PixieSaurus: Currently tiny but teeming with malevolent potential. A small investment today could mean world domination tomorrow.",
                BigDecimal.valueOf(2_100_000_000), "Unknown", "Female", 1, 1);
        var SQ = new Dinosaur("SkySquirt", "Sure, its wings are small now, but just wait until it's swooping down to snatch up whatever—or whoever—you please. The perfect pet for your malevolent plans.",
                BigDecimal.valueOf(300_000), "Pterodactyl", "Female", 0.5, 10);
        var TF = new Dinosaur("TerraFly", "At 5 years old, TerraFly has a 20-foot wingspan and the beginnings of a crest, making him your ideal wingman for airborne schemes and sky-high villainy.",
                BigDecimal.valueOf(30_000_000), "Quetzalcoatlus", "Male", 5, 200);
        var HH =  new Dinosaur("HellHorn", "With horns aplenty and a massive shield-like frill, HellHorn is your go-to for a natural battering ram. Perfect for overthrowing small kingdoms or puncturing your enemies' egos.",
                BigDecimal.valueOf(20_000_000), "Triceratops", "Female", 5, 3000);
        var SC = new Dinosaur("ShadowClaw", "At the ripe age of seven, ShadowClaw has mastered the art of ambush and evasion, making him the perfect asset for your covert operations or general reign of terror.",
                BigDecimal.valueOf(10_000_000), "Velociraptor", "Female", 7, 25);
        var BR = new Dinosaur("BlueReaper", "Say hello to BlueReaper: a blue behemoth with an appetite for destruction. The ultimate status symbol for any aspiring villain.",
                BigDecimal.valueOf(500_000_000), "Spinosaurus", "Male",9,  5000);
        var SG = new Dinosaur("SkyGrazer", "SkyGrazer: High enough to see your dreams, massive enough to crush them. This gentle giant has a dark side as vast as the skies it grazes.",
                BigDecimal.valueOf(50_000_000), "Brachiosaurus", "Male", 30, 80_000);
        var TW = new Dinosaur("TailWhipper", "TailWhipper: Eats plants, but shatters dreams. Swing this tale of darkness into your collection and let the nightmares begin!",
                BigDecimal.valueOf(500_000), "Diplodocus", "Female", 15,120_000);

        PX.setImageResource("/api/v1/images/magic-baby-dino.jpg");
        SQ.setImageResource("/api/v1/images/pterodactyl_baby.jpg");
        TF.setImageResource("/api/v1/images/quetzalcoatlus.jpg");
        HH.setImageResource("/api/v1/images/triceratops.jpg");
        SC.setImageResource("/api/v1/images/velociraptor.jpg");
        BR.setImageResource("/api/v1/images/spinosaurus.jpg");
        SG.setImageResource("/api/v1/images/brachiosaurus.jpg");
        TW.setImageResource("/api/v1/images/diplodocus.jpg");

        dinosaurRepository.saveAll(List.of(PX, SQ, TF, HH, SC, BR, SG, TW));
    }

}
