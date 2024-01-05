package se.pbt.dinoauction.dataloader;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import se.pbt.dinoauction.factory.UserFactory;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;
import se.pbt.dinoauction.model.entity.user.security.Role;
import se.pbt.dinoauction.repository.DinosaurRepository;
import se.pbt.dinoauction.repository.RoleRepository;
import se.pbt.dinoauction.repository.UserRepository;


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
        userRepository.deleteAll();
        var userFactory = new UserFactory(roleRepository);
        roleRepository.save(new Role("ROLE_ADMIN"));
        var practiceUser = userFactory.createOrganizer("admin", passWordEncoder.encode("admin"));
        userRepository.save(practiceUser);
    }

    private void loadTestStorage() {
        var PX = new Dinosaur("PixieSaurus", "Unknown", "Female", 1, 1,
                "Meet the Juvenile PixieSaurus: Currently tiny but teeming with malevolent potential." +
                        " A small investment today could mean world domination tomorrow.", BigDecimal.valueOf(2_100_000_000));

        var SQ = new Dinosaur("SkySquirt", "Pterodactyl", "Female", 1, 1,
                "Sure, its wings are small now, but just wait until it's swooping down to snatch up whatever—or whoever—you please. " +
                        "The perfect pet for your malevolent plans.", BigDecimal.valueOf(300_000));

        var TF = new Dinosaur("TerraFly", "Quetzalcoatlus", "Female", 5, 200,
                "At 5 years old, TerraFly has a 20-foot wingspan and the beginnings of a crest, " +
                        "making him your ideal wingman for airborne schemes and sky-high villainy.", BigDecimal.valueOf(30_000_000));

        var HH =  new Dinosaur("HellHorn", "Triceratops", "Female", 5, 3000,
                "With horns aplenty and a massive shield-like frill, HellHorn is your go-to for a natural battering ram. " +
                        "Perfect for overthrowing small kingdoms or puncturing your enemies' egos.", BigDecimal.valueOf(20_000_000));

        var SC = new Dinosaur("ShadowClaw", "Velociraptor", "Female", 7, 28,
                "At the ripe age of seven, ShadowClaw has mastered the art of ambush and evasion, " +
                        "making him the perfect asset for your covert operations or general reign of terror.", BigDecimal.valueOf(10_000_000));

        var BR = new Dinosaur("BlueReaper", "Spinosaurus", "Male", 9, 5000,
                "Say hello to BlueReaper: a blue behemoth with an appetite for destruction. " +
                        "The ultimate status symbol for any aspiring villain.", BigDecimal.valueOf(500_000_000));

        var SG = new Dinosaur("SkyGrazer", "Brachiosaurus", "Male", 30, 80_000,
                "SkyGrazer: High enough to see your dreams, massive enough to crush them. " +
                        "This gentle giant has a dark side as vast as the skies it grazes.", BigDecimal.valueOf(50_000_000));

        var TW = new Dinosaur("TailWhipper", "Diplodocus", "Female", 15, 120_000,
                "TailWhipper: Eats plants, but shatters dreams. Swing this tale of darkness into your collection" +
                        " and let the nightmares begin!", BigDecimal.valueOf(500_000));


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
