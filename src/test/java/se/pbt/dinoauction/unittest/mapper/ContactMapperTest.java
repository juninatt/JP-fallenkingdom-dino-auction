package se.pbt.dinoauction.unittest.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.pbt.dinoauction.mapper.ContactMapper;
import se.pbt.dinoauction.testobject.TestObjectFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ContactMapper tests:")
public class ContactMapperTest {

    private final ContactMapper mapper = ContactMapper.INSTANCE;


        @Test
        @DisplayName("Maps Contact entity to a ContactDTO correctly")
        public void testContact_to_contactDTO() {
            var contact = TestObjectFactory.contact();

            var dto = mapper.toContactDTO(contact);

            assertEquals(contact.getSecureEmail(), dto.secureEmail());
            assertEquals(contact.getPhoneNumber(), dto.phoneNumber());
            assertEquals(contact.getParticipant(), dto.participant());
            assertEquals(contact.getPhoneNumber(), dto.phoneNumber());
        }

        @Test
        @DisplayName("Maps ContactDTO to Contact entity correctly")
        public void testContactDTO_to_contact() {
            var customerContactDTO = TestObjectFactory.contactDTO();

            var customerContact = mapper.toContact(customerContactDTO);

            assertEquals(customerContactDTO.secureEmail(), customerContact.getSecureEmail());
            assertEquals(customerContactDTO.phoneNumber(), customerContact.getPhoneNumber());
            assertEquals(customerContactDTO.participant(), customerContact.getParticipant());
        }
}
