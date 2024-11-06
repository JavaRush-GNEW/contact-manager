package ua.com.javarush.gnew.m2.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDto {
  private long id;

  private String fullName;

  private List<String> phones;

  private List<String> emails;
}
