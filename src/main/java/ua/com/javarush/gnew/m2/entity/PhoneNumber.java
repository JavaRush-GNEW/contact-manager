package ua.com.javarush.gnew.m2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PhoneNumber {
    String phone;

    public PhoneNumber(String phone) {
        if(phone.matches("^\\(?(\\+?\\d{1,3})?\\)?[-.\\s]?(\\d{2,3})?[-.\\s]?\\d{3}[-.\\s]?\\d{2}[-.\\s]?\\d{2}$")){
            this.phone = phone;
        }else {
            throw new RuntimeException();
        }

    }

    public static List<PhoneNumber> mapToPhoneNumber(List<String> phones){
        return phones.stream()
                .map(s->new PhoneNumber(s))
                .collect(Collectors.toList());
    }
    public static List<String> mapToPhoneString(List<PhoneNumber> phoneNumbers){
        return phoneNumbers.stream()
                .map(p->p.getPhone())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return phone;
    }
}
