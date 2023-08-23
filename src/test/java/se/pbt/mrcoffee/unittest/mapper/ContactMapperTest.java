package se.pbt.mrcoffee.unittest.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.pbt.mrcoffee.mapper.ContactMapper;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ContactMapper tests:")
public class ContactMapperTest {

    private final ContactMapper mapper = ContactMapper.INSTANCE;


    @Nested
    @DisplayName("CustomerContact mapping tests:")
    class CustomerContactMapperTest {

        @Test
        @DisplayName("Maps CustomerContact to CustomerContactDTO correctly")
        public void testCustomerContact_to_customerContactDTO() {
            var customerContact = TestObjectFactory.createCustomerContact();

            var customerContactDTO = mapper.toCustomerContactDTO(customerContact);

            assertEquals(customerContact.getFirstName(), customerContactDTO.firstName());
            assertEquals(customerContact.getLastName(), customerContactDTO.lastName());
            assertEquals(customerContact.getEmail(), customerContactDTO.email());
            assertEquals(customerContact.getPhoneNumber(), customerContactDTO.phoneNumber());
            assertEquals(customerContact.getAdditionalInfo(), customerContactDTO.additionalInfo());
        }

        @Test
        @DisplayName("Maps CustomerContactDTO to CustomerContact correctly")
        public void testCustomerContactDTO_to_customerContact() {
            var customerContactDTO = TestObjectFactory.createCustomerContactDTO();

            var customerContact = mapper.toCustomerContact(customerContactDTO);

            assertEquals(customerContact.getFirstName(), customerContactDTO.firstName());
            assertEquals(customerContact.getFirstName(), customerContactDTO.firstName());
            assertEquals(customerContactDTO.email(), customerContact.getEmail());
            assertEquals(customerContactDTO.phoneNumber(), customerContact.getPhoneNumber());
            assertEquals(customerContactDTO.additionalInfo(), customerContact.getAdditionalInfo());
        }

        @Test
        @DisplayName("Maps CustomerContact to CustomerContactResponseDTO correctly")
        void testCustomerContact_to_customerContactResponseDTO() {
            var customerContact = TestObjectFactory.createCustomerContact();

            var responseDTO = mapper.toCustomerContactResponseDTO(customerContact);

            assertEquals(customerContact.getEmail(), responseDTO.email());
            assertEquals(customerContact.getPhoneNumber(), responseDTO.phoneNumber());
            assertEquals(customerContact.getAdditionalInfo(), responseDTO.additionalInfo());
            assertEquals(customerContact.getAddresses().size(), responseDTO.addresses().size());
        }
    }

    @Nested
    @DisplayName("SupplierContact mapping tests:")
    class SupplierContactMapperTest {

        @Test
        @DisplayName("Maps SupplierContact to SupplierContactDTO correctly")
        public void testSupplierContactToSupplierContactDTO() {
            var supplierContact = TestObjectFactory.createSupplierContact();

            var supplierContactDTO = mapper.toSupplierContactDTO(supplierContact);

            assertEquals(supplierContact.getEmail(), supplierContactDTO.email());
            assertEquals(supplierContact.getPhoneNumber(), supplierContactDTO.phoneNumber());
            assertEquals(supplierContact.getAdditionalInfo(), supplierContactDTO.additionalInfo());
            assertEquals(supplierContact.getCompanyName(), supplierContactDTO.companyName());
            assertEquals(supplierContact.getIndustry(), supplierContactDTO.industry());
        }

        @Test
        @DisplayName("Maps SupplierContact to SupplierContactResponseDTO correctly")
        public void testSupplierContactToSupplierContactResponseDTO() {
            var supplierContact = TestObjectFactory.createSupplierContact();

            var responseDTO = mapper.toSupplierContactResponseDTO(supplierContact);

            assertEquals(supplierContact.getEmail(), responseDTO.email());
            assertEquals(supplierContact.getPhoneNumber(), responseDTO.phoneNumber());
            assertEquals(supplierContact.getAdditionalInfo(), responseDTO.additionalInfo());
            assertEquals(supplierContact.getCompanyName(), responseDTO.companyName());
            assertEquals(supplierContact.getIndustry(), responseDTO.industry());
        }

        @Test
        @DisplayName("Maps SupplierContactDTO to SupplierContact correctly")
        public void testSupplierContactDTOToSupplierContact() {
            var supplierContactDTO = TestObjectFactory.createSupplierContactDTO();

            var supplierContact = mapper.toSupplierContact(supplierContactDTO);

            assertEquals(supplierContactDTO.email(), supplierContact.getEmail());
            assertEquals(supplierContactDTO.phoneNumber(), supplierContact.getPhoneNumber());
            assertEquals(supplierContactDTO.additionalInfo(), supplierContact.getAdditionalInfo());
            assertEquals(supplierContactDTO.companyName(), supplierContact.getCompanyName());
            assertEquals(supplierContactDTO.industry(), supplierContact.getIndustry());
        }
    }
}
