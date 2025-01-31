package ua.com.javarush.gnew.m2.cli.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListContactsTest {
    @Mock
    private PhoneBookInterface phoneBookInterface;

    private ListContacts listContacts;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        listContacts = new ListContacts() {
            @Override
            public Integer call() throws IOException {

                phoneBookInterface.list().forEach(contact -> System.out.println(contact.getFullName()));
                return 0;
            }
        };

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testListContacts() throws IOException {

        ContactDto contact = new ContactDto("John Doe", List.of("123-456-7890"), List.of(), "");
        List<ContactDto> contacts = List.of(contact);
        when(phoneBookInterface.list()).thenReturn(contacts);

        int result = listContacts.call();

        verify(phoneBookInterface, times(1)).list();

        String expectedOutput = "John Doe";
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());

        assertEquals(0, result);
    }
}