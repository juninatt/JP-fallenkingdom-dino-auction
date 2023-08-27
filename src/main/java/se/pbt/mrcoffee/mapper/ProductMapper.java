package se.pbt.mrcoffee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se.pbt.mrcoffee.dto.request.CoffeeDTO;
import se.pbt.mrcoffee.model.product.Coffee;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    /**
     * Maps an {@link CoffeeDTO} to a {@link Coffee} entity.
     * @param coffeeDTO The {@link CoffeeDTO} to convert.
     * @return The corresponding {@link Coffee}.
     */
    Coffee toCoffee(CoffeeDTO coffeeDTO);

    /**
     * Maps an {@link Coffee} to a {@link CoffeeDTO}.
     * @param coffeeEntity The {@link Coffee} entity to convert.
     * @return The corresponding {@link CoffeeDTO}.
     */
    CoffeeDTO toCoffeeDTO(Coffee coffeeEntity);
}
