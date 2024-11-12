package ua.com.javarush.gnew.m2.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.entity.Contact;
import ua.com.javarush.gnew.m2.entity.Email;
import ua.com.javarush.gnew.m2.entity.GitHubID;
import ua.com.javarush.gnew.m2.entity.PhoneNumber;

@Mapper
public interface ContactMapper {

  ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

  @Mapping(target = "phones", source = "phones")
  @Mapping(target = "emails", source = "emails")
  @Mapping(target = "githubId", source = "githubId")
  ContactDto toDto(Contact contactEntity);

  @Mapping(target = "phones", source = "phones")
  @Mapping(target = "emails", source = "emails")
  @Mapping(target = "githubId", source = "githubId")
  Contact toEntity(ContactDto contactDto);

  default List<String> mapPhonesToStrings(List<PhoneNumber> phones) {
    return phones.stream().map(PhoneNumber::getPhone).collect(Collectors.toList());
  }

  default List<PhoneNumber> mapStringsToPhones(List<String> phoneStrings) {
    return phoneStrings.stream().map(PhoneNumber::new).collect(Collectors.toList());
  }

  default List<String> mapEmailsToStrings(List<Email> emails) {
    return emails.stream().map(Email::getEmail).collect(Collectors.toList());
  }

  default List<Email> mapStringsToEmails(List<String> emailStrings) {
    return emailStrings.stream().map(Email::new).collect(Collectors.toList());
  }

  default String mapGitHubIdToString(GitHubID githubId) {
    return githubId.getGithubId();
  }

  default GitHubID mapStringToGitHubId(String githubId) {
    return new GitHubID(githubId);
  }
}
