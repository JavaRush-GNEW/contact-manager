package javarush.com.ua.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private List<String> roles;

    public User() {}

    public User(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

}
