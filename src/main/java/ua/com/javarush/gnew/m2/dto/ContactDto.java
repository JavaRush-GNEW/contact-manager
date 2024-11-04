package ua.com.javarush.gnew.m2.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ContactDto  {
    private long id;

    private String fullName;

    private List<String> phones;

    private List<String> emails;

    @JsonCreator
    public ContactDto(@JsonProperty("id") long id,
                      @JsonProperty("fullName") String fullName,
                      @JsonProperty("phones") List<String> phones,
                      @JsonProperty("emails") List<String> emails) {
        this.id = id;
        this.fullName = fullName;
        this.phones = phones;
        this.emails = emails;
    }

}
