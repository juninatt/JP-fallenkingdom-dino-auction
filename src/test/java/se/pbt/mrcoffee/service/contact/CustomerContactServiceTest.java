package se.pbt.mrcoffee.service.contact;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.pbt.mrcoffee.messaging.JmsMessageProducer;
import se.pbt.mrcoffee.model.contact.CustomerContact;
import se.pbt.mrcoffee.repository.contact.CustomerContactRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CustomerContactService:")
class CustomerContactServiceTest {

    @Mock
    private CustomerContactRepository contactRepository;

    @Mock
    private JmsMessageProducer jmsMessageProducer;

    @InjectMocks
    private CustomerContactService contactService;

    @Nested
    @DisplayName("createPrivateCustomerContact():")
    class CreatePrivateCustomerContactTests {

        @Test
        @DisplayName("Returns created CustomerContact and sends JMS message")
        void createPrivateCustomerContact_validInput_returnsCreatedContact() {
            // Arrange
            var inputContact = new CustomerContact();

            var createdContact = new CustomerContact();
            when(contactRepository.save(inputContact)).thenReturn(createdContact);

            // Act
            var result = contactService.createPrivateCustomerContact(inputContact);

            // Assert
            assertNotNull(result);
            assertSame(createdContact, result);
            verify(jmsMessageProducer).sendMessage("myQueue", "Private customer contact created: " + createdContact.getEmail());
        }

        @Test
        @DisplayName("Saves the CustomerContact to the repository")
        void createPrivateCustomerContact_validInput_savesContactToRepository() {
            // Arrange
            var inputContact = new CustomerContact();

            var createdContact = new CustomerContact();
            when(contactRepository.save(inputContact)).thenReturn(createdContact);

            // Act
            contactService.createPrivateCustomerContact(inputContact);

            // Assert
            verify(contactRepository).save(inputContact);
        }
    }


}
