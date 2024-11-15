package javarush.com.ua.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ContactEntity {
    @Id
    private String id;

    private String user;

    private String fullName;

    private List<String> phones;

    private List<String> emails;

    private String githubId;
}
