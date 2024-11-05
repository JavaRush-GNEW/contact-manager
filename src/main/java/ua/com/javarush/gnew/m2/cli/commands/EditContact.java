package ua.com.javarush.gnew.m2.cli.commands;

import picocli.CommandLine;
import ua.com.javarush.gnew.m2.cli.CliCommand;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.utils.Utils;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import static picocli.CommandLine.Command;
import static picocli.CommandLine.Parameters;

@Command(name = "--edit", aliases = {"-e", "--edit"},
        description = "Редагує існуючий контакт за ім'ям")
public  class EditContact implements CliCommand {
    private final PhoneBookInterface phoneBookInterface;

    Scanner scanner = new Scanner(System.in);

    String choice = "";

    @Parameters(index = "0", description = "ID контакта", arity = "1")
    private long id;

    public EditContact(PhoneBookInterface phoneBookInterface) {
        this.phoneBookInterface = phoneBookInterface;
    }

    @Override
    public Integer call() {

        Optional<ContactDto> optionalContact = phoneBookInterface.getById(id);

        if (optionalContact.isPresent()) {
            ContactDto contactDto = optionalContact.get();
            Utils.printContactList(List.of(contactDto));

            while (true) {
                System.out.println("\nТелефонна книга - виберiть команду:");
                System.out.println("1. Редагувати iм'я");
                System.out.println("2. Редагувати телефони");
                System.out.println("3. Редагувати email");
                System.out.println("4. Вийти");
                System.out.print("Ваш вибiр: ");
                choice = scanner.nextLine();
                if (choice.equals("4")) return 0;
                new CommandLine(new EditContactMenu(phoneBookInterface, contactDto)).execute(choice);
            }
        } else System.out.println("Помилка. Такого контакту не існує. Спробуйте ще раз.");


        return 0;
    }
}
