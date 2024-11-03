package ua.com.javarush.gnew.m2.cli.commands;

import ua.com.javarush.gnew.m2.cli.CliCommand;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.utils.Utils;
import java.util.List;
import static picocli.CommandLine.*;

@Command(name = "add", aliases = {"-a", "--add"},
        description = "Додає новий контакт до телефонної книги",
        mixinStandardHelpOptions = true)
public class AddContact implements CliCommand {

    private final PhoneBookInterface phoneBookInterface;

    @Option(names = {"-n", "--name"}, description = "Ім'я контакту", required = true)
    private String name;

    @Option(names = {"-p", "--phone"}, description = "Номер телефону", required = true, arity = "0..3")
    private List<String> phones;

    @Option(names = {"-e", "--email"},
            description = "Електронна пошта",
            required = true,
            arity = "0..3")
    private List<String> emails;

    public AddContact(PhoneBookInterface phoneBookInterface) {
        this.phoneBookInterface = phoneBookInterface;
    }

    @Override
    public Integer call() {
        try {
            ContactDto newContactDto = ContactDto.builder()
                    .fullName(name)
                    .phones(phones)
                    .emails(emails)
                    .build();
            ContactDto savedContactDto = phoneBookInterface.add(newContactDto);
            System.out.println("Контакт додано: ");
            Utils.printContactList(List.of(savedContactDto));

        } catch (Exception e) {
            System.out.println("Помилка, невірний формат: " + name);
        }
        return 0;
    }
}

