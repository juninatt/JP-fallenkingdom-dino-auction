package se.pbt.dinoauction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se.pbt.dinoauction.dto.ContactDTO;
import se.pbt.dinoauction.model.contact.Contact;


/**
 * Mapper interface for {@link Contact}-related objects.
 */
@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    /**
     * Maps a {@link Contact} entity to a {@link ContactDTO}.
     *
     * @param contactEntity The Contact entity.
     * @return The corresponding DTO.
     */
    ContactDTO toContactDTO(Contact contactEntity);

    /**
     * Maps a {@link ContactDTO} to a {@link Contact} entity.
     *
     * @param contactDTO The ContactDTO
     * @return The corresponding Contact entity
     */
    Contact toContact(ContactDTO contactDTO);
}

