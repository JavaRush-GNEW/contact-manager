package ua.com.javarush.gnew.m2.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.entity.Contact;
import ua.com.javarush.gnew.m2.entity.Email;
import ua.com.javarush.gnew.m2.entity.PhoneNumber;

class ContactMapperTest {

  private final ContactMapper mapper = ContactMapper.INSTANCE;

  @Test
  void shouldMapContactToContactDto() {
    // Given
    List<PhoneNumber> phoneNumbers =
        Arrays.asList(new PhoneNumber("123-456-7890"), new PhoneNumber("098-765-4321"));
    List<Email> emails =
        Arrays.asList(new Email("test@example.com"), new Email("test2@example.com"));
    Contact contact = new Contact();
    contact.setId(1L);
    contact.setFullName("John Doe");
    contact.setPhones(phoneNumbers);
    contact.setEmails(emails);

    // When
    ContactDto contactDto = mapper.toDto(contact);

    // Then
    assertNotNull(contactDto);
    assertEquals(contact.getId(), contactDto.getId());
    assertEquals(contact.getFullName(), contactDto.getFullName());
    assertEquals(Arrays.asList("123-456-7890", "098-765-4321"), contactDto.getPhones());
    assertEquals(Arrays.asList("test@example.com", "test2@example.com"), contactDto.getEmails());
  }

  @Test
  void shouldMapContactDtoToContact() {
    // Given
    List<String> phoneStrings = Arrays.asList("123-456-7890", "098-765-4321");
    List<String> emailStrings = Arrays.asList("test@example.com", "test2@example.com");
    ContactDto contactDto = new ContactDto(1L, "John Doe", phoneStrings, emailStrings, "JohnGit");
    // When
    Contact contact = mapper.toEntity(contactDto);

    // Then
    assertNotNull(contact);
    assertEquals(contactDto.getId(), contact.getId());
    assertEquals(contactDto.getFullName(), contact.getFullName());

    // Verify phone numbers
    assertEquals(2, contact.getPhones().size());
    assertEquals("123-456-7890", contact.getPhones().get(0).getPhone());
    assertEquals("098-765-4321", contact.getPhones().get(1).getPhone());

    // Verify emails
    assertEquals(2, contact.getEmails().size());
    assertEquals("test@example.com", contact.getEmails().get(0).getEmail());
    assertEquals("test2@example.com", contact.getEmails().get(1).getEmail());
  }
}
