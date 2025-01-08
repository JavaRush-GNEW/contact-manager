package ua.com.javarush.gnew.m2.cli.commands;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;

class EditContactMenuTest {


    @ParameterizedTest
    @CsvSource({"1, New Name", "2, 987654321", "3, new.email@example.com", "4, newGithubID", "5, invalid"})
    void testEditContactMenuValidAndInvalidInput(String userChoice, String userInput) throws IOException {

       PhoneBookInterface phoneBookInterface = mock(PhoneBookInterface.class);

        try (MockedStatic<PhoneBookContext> mockedStatic = mockStatic(PhoneBookContext.class)) {
            mockedStatic.when(() -> PhoneBookContext.getBean(PhoneBookInterface.class))
                    .thenReturn(phoneBookInterface);

            ContactDto contact = new ContactDto();
            contact.setId(1L);
            contact.setFullName("John Doe");
            contact.setPhones(List.of("123456789"));
            contact.setEmails(List.of("john.doe@example.com"));
            contact.setGithubId("johndoe123");

            EditContactMenu editContactMenu = new EditContactMenu();

            editContactMenu.setContact(contact);

            when(phoneBookInterface.getById(1L)).thenReturn(Optional.of(contact));

            editContactMenu.setChoice(userChoice);

            editContactMenu.setScanner(new Scanner(userInput));

            Integer result = editContactMenu.call();

            assertEquals(0, result);

            ContactDto updatedContact = editContactMenu.getContact();

            String ONE = "1";
            String TWO = "2";
            String THREE = "3";
            String FOUR = "4";
            String FIVE = "5";

            String expectedName = "New Name";
            String expectedPhone = "987654321";
            String expectedEmail = "new.email@example.com";
            String expectedGitHubID = "newGitHubID";
            String expectedInput = "invalid";


            if (userChoice.equals(ONE) && userInput.equals(expectedName)) {
                    assertEquals(expectedName, updatedContact.getFullName());
                    verify(phoneBookInterface, times(1)).edit(updatedContact);

            } else if (userChoice.equals(TWO) && userInput.equals(expectedPhone)) {
                assertEquals(List.of(expectedPhone), updatedContact.getPhones());
                verify(phoneBookInterface, times(1)).edit(updatedContact);

            } else if (userChoice.equals(THREE) && userInput.equals(expectedEmail)) {
                assertEquals(List.of(expectedEmail), updatedContact.getEmails());
                verify(phoneBookInterface, times(1)).edit(updatedContact);

            } else if (userChoice.equals(FOUR) && userInput.equals(expectedGitHubID)) {
                assertEquals(expectedGitHubID, updatedContact.getGithubId());
                verify(phoneBookInterface, times(1)).edit(updatedContact);

            } else if (userChoice.equals(FIVE) && userInput.equals(expectedInput)) {
                verifyNoInteractions(phoneBookInterface);
            }
            
        }
    }

}