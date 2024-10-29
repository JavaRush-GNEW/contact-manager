package ua.com.javarush.gnew.m2.cli;


import picocli.CommandLine;
import ua.com.javarush.gnew.m2.entity.Contact;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.utils.Utils;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "--edit-menu", description = "Редагує існуючий контакт за ім'ям")
public class EditContactMenu implements Callable<Integer> {
    private final PhoneBookInterface phoneBookInterface;
    private final Contact contact;
    Scanner scanner = new Scanner(System.in);
    @CommandLine.Parameters(index = "0", description = "ID контакта", arity = "1")
    private String choice;


    public EditContactMenu(PhoneBookInterface phoneBookInterface, Contact contact) {
        this.phoneBookInterface = phoneBookInterface;
        this.contact = contact;
    }

    @Override
    public Integer call(){

        switch (choice) {
            case "1":
                System.out.print("1. Введiть нове iм'я: ");
                String change = scanner.nextLine();
                contact.setFullName(change);
                phoneBookInterface.edit(contact);
                Utils.printContactList(List.of(contact));
                break;
            case "2":

                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                System.out.println("Вихід з програми.");
                choice = "exit"; // Завершуємо цикл
                break;
            default:
                System.out.println("Неправильний вибір. Спробуйте ще раз.");
        }

        return 0;
    }
}
