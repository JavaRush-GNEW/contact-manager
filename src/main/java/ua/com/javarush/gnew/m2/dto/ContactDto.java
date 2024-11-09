package ua.com.javarush.gnew.m2.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
  private long id;

  private String fullName;

  private List<String> phones;

  private List<String> emails;

  public ContactDto(String fullName, List<String> phones, List<String> emails) {
    this.fullName = fullName;
    this.phones = phones;
    this.emails = emails;
  }
}
