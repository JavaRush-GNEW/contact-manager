package ua.com.javarush.gnew.m2.cli.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import picocli.CommandLine;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddContactTest {
    @Mock
    private PhoneBookInterface phoneBookInterface;

    @InjectMocks
    private AddContact addContact;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {

        MockitoAnnotations.openMocks(this);

        addContact = new AddContact();

        Field phoneBookInterfaceField = AddContact.class.getDeclaredField("phoneBookInterface");
        phoneBookInterfaceField.setAccessible(true);
        phoneBookInterfaceField.set(addContact, phoneBookInterface);
    }

    @Test
    void testCall_addContactSuccessfully() throws Exception {

        ContactDto savedContact = new ContactDto("Иван Иванов", List.of("123456789"), List.of("ivan@example.com"), "ivanGitHub");
        when(phoneBookInterface.add(any(ContactDto.class))).thenReturn(savedContact);

        String[] args = {
                "--name", "Иван Иванов",
                "--phone", "123456789",
                "--email", "ivan@example.com",
                "--github", "ivanGitHub"
        };
        CommandLine.populateCommand(addContact, args);

        Integer result = addContact.call();

        verify(phoneBookInterface, times(1)).add(any(ContactDto.class));

        assertEquals(0, result);
    }
    @Test
    void testCall_withException() throws IOException {

        when(phoneBookInterface.add(any(ContactDto.class))).thenThrow(new RuntimeException("Ошибка добавления"));

        String[] args = {
                "--name", "Иван Иванов",
                "--phone", "123456789"
        };
        CommandLine.populateCommand(addContact, args);

        RuntimeException thrownException = null;
        try {
            addContact.call();
        } catch (RuntimeException e) {
            thrownException = e;
        }

        assertEquals("java.lang.RuntimeException: Ошибка добавления", thrownException.getMessage());

        verify(phoneBookInterface, times(1)).add(any(ContactDto.class));
 }
}