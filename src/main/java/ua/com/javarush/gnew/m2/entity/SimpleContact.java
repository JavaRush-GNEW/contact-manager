package ua.com.javarush.gnew.m2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


import static ua.com.javarush.gnew.m2.entity.Email.mapToEmail;
import static ua.com.javarush.gnew.m2.entity.Email.mapToEmailString;
import static ua.com.javarush.gnew.m2.entity.PhoneNumber.mapToPhoneNumber;
import static ua.com.javarush.gnew.m2.entity.PhoneNumber.mapToPhoneString;

@Data
@NoArgsConstructor
public class SimpleContact implements Contact {
    /**
     * TODO: Add normal id generator.
     */

    private String id = String.valueOf((long) (Math.random()*10000));
    private String fullName;
    private List<PhoneNumber> phones;
    private List<Email> emails;

    public SimpleContact(String fullName, List<String> phones, List<String> emails) {
        this.fullName = fullName;
        this.phones = mapToPhoneNumber(phones);
        this.emails = mapToEmail(emails);
    }

    public SimpleContact(String id, String fullName,List<String> phones, List<String> emails) {
        this.id = id;
        this.fullName = fullName;
        this.phones = mapToPhoneNumber(phones);
        this.emails = mapToEmail(emails);
    }

    public List<String> getPhones() {
        return mapToPhoneString(phones);
    }

    public void setPhones(List<String> phones) {
        this.phones = mapToPhoneNumber(phones);
    }

    public List<String> getEmails() {
        return mapToEmailString(emails);
    }

    public void setEmails(List<String> emails) {
        this.emails = mapToEmail(emails);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phones=" + phones +
                ", emails=" + emails +
                '}';
    }

//    private List<PhoneNumber> mapToPhoneNumber(List<String> phones){
//       return phones.stream()
//                .map(s->new PhoneNumber(s))
//                .collect(Collectors.toList());
//    }
//    private List<String> mapToPhoneString(List<PhoneNumber> phoneNumbers){
//        return phoneNumbers.stream()
//                .map(p->p.toString())
//                .collect(Collectors.toList());
//    }

}
