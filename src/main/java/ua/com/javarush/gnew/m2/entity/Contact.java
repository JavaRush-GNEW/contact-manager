package ua.com.javarush.gnew.m2.entity;

import java.util.List;
import lombok.Data;

@Data
public class Contact {
  private long id;

  private String fullName;

  private List<PhoneNumber> phones;

  private List<Email> emails;
}
