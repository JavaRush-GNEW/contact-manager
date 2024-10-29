package ua.com.javarush.gnew.m2.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Email {
    private String email;

    public Email(String email) {
        this.email = email;
    }

    public static List<Email> mapToEmail(List<String> emails){
        return emails.stream()
                .map(Email::new)
                .collect(Collectors.toList());
    }
    public static List<String> mapToEmailString(List<Email> emails){
        return emails.stream()
                .map(Email::getEmail)
                .collect(Collectors.toList());
    }



}
