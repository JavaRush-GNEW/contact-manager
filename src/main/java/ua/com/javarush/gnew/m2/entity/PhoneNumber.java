package ua.com.javarush.gnew.m2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhoneNumber {
  String phone;

  public PhoneNumber(String phone) {
    if (phone.matches(
        "^\\(?(\\+?\\d{1,3})?\\)?[-.\\s]?(\\d{2,3})?[-.\\s]?\\d{3}[-.\\s]?\\d{2}[-.\\s]?\\d{2}$")) {
      this.phone = phone;
    } else {
      throw new IllegalArgumentException("Invalid phone number format: " + phone);
    }
  }
}
