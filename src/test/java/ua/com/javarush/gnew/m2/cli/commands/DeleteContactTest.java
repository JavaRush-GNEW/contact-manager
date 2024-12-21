package ua.com.javarush.gnew.m2.cli.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import picocli.CommandLine;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DeleteContactTest {

    @Mock
    private PhoneBookInterface phoneBookInterface;

    private DeleteContact deleteContact;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        deleteContact = new DeleteContact();
        var field = DeleteContact.class.getDeclaredField("phoneBookInterface");
        field.setAccessible(true);
        field.set(deleteContact, phoneBookInterface);
    }

    @Test
    void testCall_successfulDeletion() throws Exception {

        doNothing().when(phoneBookInterface).delete(anyLong());
        when(phoneBookInterface.list()).thenReturn(List.of());

        String[] args = {"123", "456", "789"};
        CommandLine.populateCommand(deleteContact, args);

        Integer result = deleteContact.call();

        verify(phoneBookInterface, times(1)).delete(123L);
        verify(phoneBookInterface, times(1)).delete(456L);
        verify(phoneBookInterface, times(1)).delete(789L);

        verify(phoneBookInterface, times(1)).list();

        assert result == 0;
    }

    @Test
    void testCall_deletionWithException() throws Exception {

        doNothing().when(phoneBookInterface).delete(123L);
        doThrow(new IOException("Ошибка удаления")).when(phoneBookInterface).delete(456L);

        String[] args = {"123", "456"};
        CommandLine.populateCommand(deleteContact, args);

        Integer result = deleteContact.call();

        verify(phoneBookInterface, times(1)).delete(123L);
        verify(phoneBookInterface, times(1)).delete(456L);

        verify(phoneBookInterface, times(1)).list();

        assert result == 0;
    }
}