package javarush.com.ua.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ContactDto {
  @Id
  private String id;

  private String user;

  private String fullName;

  private List<String> phones;

  private List<String> emails;

  private String githubId;

 }
