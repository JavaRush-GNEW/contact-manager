package ua.com.javarush.gnew.m2.cli.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import picocli.CommandLine;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.ArgumentMatchers.any;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


class EditContactTest {

    private PhoneBookInterface phoneBookInterface;

    private EditContactMenu editContactMenu;


    @BeforeEach
    void setUp() {

        phoneBookInterface = mock(PhoneBookInterface.class);
        editContactMenu = mock(EditContactMenu.class);
    }

    @Test
    void testEditContactSuccessfulScenario() throws IOException {

        try (MockedStatic<PhoneBookContext> mockedStatic = mockStatic(PhoneBookContext.class)) {
            mockedStatic.when(() -> PhoneBookContext.getBean(PhoneBookInterface.class))
                    .thenReturn(phoneBookInterface);
            mockedStatic.when(() -> PhoneBookContext.getBean(EditContactMenu.class))
                    .thenReturn(editContactMenu);

            ContactDto mockContact = new ContactDto();
            mockContact.setId(1L);
            mockContact.setFullName("John Doe");
            mockContact.setPhones(List.of("123456789"));
            mockContact.setEmails(List.of("john.doe@example.com"));
            mockContact.setGithubId("johndoe123");

            when(phoneBookInterface.getById(1L)).thenReturn(Optional.of(mockContact));

            String userInput = "5\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes()));

            EditContact editContact = new EditContact();

            new CommandLine(editContact).parseArgs("1");

            Integer result = editContact.call();

            assertEquals(0, result);

            verify(phoneBookInterface, times(1)).getById(1L);

            verify(editContactMenu, never()).setContact(any(ContactDto.class));
        }
    }

    @Test
    void testEditContactNotFound() throws IOException {

        try (MockedStatic<PhoneBookContext> mockedStatic = mockStatic(PhoneBookContext.class)) {
            mockedStatic.when(() -> PhoneBookContext.getBean(PhoneBookInterface.class))
                    .thenReturn(phoneBookInterface);

            when(phoneBookInterface.getById(1L)).thenReturn(Optional.empty());

            String userInput = "5\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes()));

            EditContact editContact = new EditContact();

            new CommandLine(editContact).parseArgs("1");

            Integer result = editContact.call();

            assertEquals(0, result);

            verify(phoneBookInterface, times(1)).getById(1L);

            verifyNoInteractions(editContactMenu);

        }
    }

    @Test
    void testEditContact_ValidEdit() throws IOException {

        try (MockedStatic<PhoneBookContext> mockedStatic = mockStatic(PhoneBookContext.class)) {
            mockedStatic.when(() -> PhoneBookContext.getBean(PhoneBookInterface.class))
                    .thenReturn(phoneBookInterface);
            mockedStatic.when(() -> PhoneBookContext.getBean(EditContactMenu.class))
                    .thenReturn(editContactMenu);

            ContactDto mockContact = new ContactDto();
            mockContact.setId(1L);
            mockContact.setFullName("John Doe");
            mockContact.setPhones(List.of("123456789"));
            mockContact.setEmails(List.of("john.doe@example.com"));
            mockContact.setGithubId("johndoe123");

            when(phoneBookInterface.getById(1L)).thenReturn(Optional.of(mockContact));

            String userInput = "1\n5\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes()));

            EditContact editContact = new EditContact();

            new CommandLine(editContact).parseArgs("1");

            Integer result = editContact.call();

            assertEquals(0, result);

            verify(phoneBookInterface, times(1)).getById(1L);

            verify(editContactMenu, times(1)).setContact(mockContact);
        }
    }

}