package ua.com.javarush.gnew.m2.cli.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import picocli.CommandLine;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.repository.GroupContactsRepository;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GroupContactTest {

    @Mock
    private GroupContactsRepository groupContactsRepository;

    @Mock
    private PhoneBookInterface phoneBookInterface;

    private GroupContact groupContact;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        groupContact = new GroupContact();

        Field groupRepoField = GroupContact.class.getDeclaredField("groupContactsRepository");
        groupRepoField.setAccessible(true);
        groupRepoField.set(groupContact, groupContactsRepository);

        Field phoneBookField = GroupContact.class.getDeclaredField("phoneBookInterface");
        phoneBookField.setAccessible(true);
        phoneBookField.set(groupContact, phoneBookInterface);
    }

    @Test
    void testCreateGroup() throws Exception {
        String[] args = {"--create", "Friends"};
        CommandLine.populateCommand(groupContact, args);

        Integer result = groupContact.call();

        verify(groupContactsRepository, times(1)).create("Friends");
        verifyNoMoreInteractions(groupContactsRepository);
        assertEquals(0, result);
    }

    @Test
    void testDeleteGroup() throws Exception {
        String[] args = {"--delete", "OldFriends"};
        CommandLine.populateCommand(groupContact, args);

        Integer result = groupContact.call();

        verify(groupContactsRepository, times(1)).delete("OldFriends");
        verifyNoMoreInteractions(groupContactsRepository);
        assertEquals(0, result);
    }

    @Test
    void testAddContactsToGroup() throws Exception {
        String[] args = {"--add", "Family", "1", "2", "3"};
        CommandLine.populateCommand(groupContact, args);

        Integer result = groupContact.call();

        verify(groupContactsRepository, times(1)).addContact("Family", 1);
        verify(groupContactsRepository, times(1)).addContact("Family", 2);
        verify(groupContactsRepository, times(1)).addContact("Family", 3);
        verifyNoMoreInteractions(groupContactsRepository);
        assertEquals(0, result);
    }

    @Test
    void testRemoveContactsFromGroup() throws Exception {
        String[] args = {"--remove", "Colleagues", "5", "6"};
        CommandLine.populateCommand(groupContact, args);

        Integer result = groupContact.call();

        verify(groupContactsRepository, times(1)).deleteContact("Colleagues", 5);
        verify(groupContactsRepository, times(1)).deleteContact("Colleagues", 6);
        verifyNoMoreInteractions(groupContactsRepository);
        assertEquals(0, result);
    }

    @Test
    void testListGroups() throws Exception {

        when(groupContactsRepository.getGroups()).thenReturn(List.of("Family", "Friends"));
        when(groupContactsRepository.getContactsId("Family")).thenReturn(List.of(1L, 2L));
        when(groupContactsRepository.getContactsId("Friends")).thenReturn(List.of(3L, 4L));

        when(phoneBookInterface.getById(1L)).thenReturn(Optional.of(
                new ContactDto(1L, "John Doe", List.of("123456789"), List.of("john.doe@example.com"), null)
        ));
        when(phoneBookInterface.getById(2L)).thenReturn(Optional.of(
                new ContactDto(2L, "Jane Doe", List.of("987654321"), List.of("jane.doe@example.com"), null)
        ));
        when(phoneBookInterface.getById(3L)).thenReturn(Optional.of(
                new ContactDto(3L, "Alice Smith", List.of("111222333"), List.of("alice@example.com"), null)
        ));
        when(phoneBookInterface.getById(4L)).thenReturn(Optional.of(
                new ContactDto(4L, "Bob Johnson", List.of("444555666"), List.of("bob@example.com"), null)
        ));

        String[] args = {};
        CommandLine.populateCommand(groupContact, args);

        Integer result = groupContact.call();

        verify(groupContactsRepository, times(1)).getGroups();
        verify(groupContactsRepository, times(1)).getContactsId("Family");
        verify(groupContactsRepository, times(1)).getContactsId("Friends");
        verify(phoneBookInterface, times(1)).getById(1L);
        verify(phoneBookInterface, times(1)).getById(2L);
        verify(phoneBookInterface, times(1)).getById(3L);
        verify(phoneBookInterface, times(1)).getById(4L);

        assertEquals(0, result);
    }
}