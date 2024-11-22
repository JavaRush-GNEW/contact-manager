package javarush.com.ua.dto;

import java.util.List;
import lombok.Data;

@Data
public class UserDto {
  private String username;
  private String password;
  private List<String> roles;
}
