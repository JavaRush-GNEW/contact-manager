package ua.com.javarush.gnew.m2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Email {
  private String email;

  public Email(String email) {
    if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
      this.email = email;
    } else {
      throw new IllegalArgumentException("Invalid email format: " + email);
    }
  }
}
