package javarush.com.ua.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ContactDto {
  @Id private Long id;

  private String fullName;

  private List<String> phones;

  private List<String> emails;

  private String githubId;
}
