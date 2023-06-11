package se.pbt.mrcoffe.dataloader;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import se.pbt.mrcoffe.model.Coffee;
import se.pbt.mrcoffe.repository.CoffeeRepository;

import java.util.List;

@Component
class DataLoader {
    private final CoffeeRepository coffeeRepository;
    public DataLoader(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }
    @PostConstruct
    private void loadData() {
        coffeeRepository.saveAll(List.of(
                new Coffee("Café Cereza"),
                new Coffee("Café Ganador"),
                new Coffee("Café Lareño"),
                new Coffee("Café Três Pontas")
        ));
    }
}
