package ua.com.javarush.gnew.m2.cli.commands;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;


class EditContactMenuTest {

    private PhoneBookInterface phoneBookInterface;
    private EditContactMenu editContactMenu;

    @BeforeEach
    void setUp() {

        phoneBookInterface = mock(PhoneBookInterface.class);
        editContactMenu = mock(EditContactMenu.class);
    }

    @Test
    void testEditContactMenuEditName() throws IOException {
        try (MockedStatic<PhoneBookContext> mockedStatic = mockStatic(PhoneBookContext.class)) {
            mockedStatic.when(() -> PhoneBookContext.getBean(PhoneBookInterface.class))
                    .thenReturn(phoneBookInterface);

            ContactDto contact = new ContactDto();
            contact.setId(1L);
            contact.setFullName("John Doe");
            contact.setPhones(List.of("123456789"));
            contact.setEmails(List.of("john.doe@example.com"));
            contact.setGithubId("johndoe123");

            editContactMenu.setContact(contact);

            when(phoneBookInterface.getById(1L)).thenReturn(Optional.of(contact));

            editContactMenu.setChoice("1");

            editContactMenu.setScanner(new Scanner("New Name"));

            Integer result = editContactMenu.call();

            assertEquals(0, result);

            ContactDto updatedContact = editContactMenu.getContact();
            assertEquals("New Name", updatedContact.getFullName());

            verify(phoneBookInterface, times(1)).edit(updatedContact);
        }
    }



    @Test
    void testEditContactMenuInvalidChoice() throws IOException {
        try (MockedStatic<PhoneBookContext> mockedStatic = mockStatic(PhoneBookContext.class)) {
            mockedStatic.when(() -> PhoneBookContext.getBean(PhoneBookInterface.class))
                    .thenReturn(phoneBookInterface);

            editContactMenu.setChoice("invalid");

            Integer result = editContactMenu.call();

            assertEquals(0, result);

            verifyNoInteractions(phoneBookInterface);
        }
    }
}