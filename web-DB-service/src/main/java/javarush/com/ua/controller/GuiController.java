package javarush.com.ua.controller;

import javarush.com.ua.dto.ContactDto;
import javarush.com.ua.entity.ContactEntity;
import javarush.com.ua.repository.ContactDtoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gui")
@RequiredArgsConstructor
public class GuiController {

  private final ContactDtoRepository contactDtoRepository;
  private final ModelMapper modelMapper;

  @GetMapping("/contacts")
  public String getContacts(@AuthenticationPrincipal Mono<UserDetails> currentUser, Model model) {
    var contacts =
        currentUser
            .map(UserDetails::getUsername)
            .flatMapMany(contactDtoRepository::findAllByUser)
            .map(this::convertToContactDto)
            .map(c -> GuiContact.of(c));

    model.addAttribute("contacts", contacts);
    model.addAttribute("user", currentUser.block().getUsername());
    return "contacts";
  }

  private ContactDto convertToContactDto(ContactEntity deviceEntity) {
    return modelMapper.map(deviceEntity, ContactDto.class);
  }

  private ContactEntity convertToContactEntity(ContactDto deviceEntity) {
    return modelMapper.map(deviceEntity, ContactEntity.class);
  }
}

@Data
class GuiContact {
  private String id;

  private String fullName;

  private String phones;

  private String emails;

  private String githubId;

  public static GuiContact of(ContactDto contactDto) {
    GuiContact guicontact = new GuiContact();
    guicontact.setId(String.valueOf(contactDto.getId()));
    guicontact.setFullName(contactDto.getFullName());
    guicontact.setPhones(contactDto.getPhones().stream().collect(Collectors.joining(", ")));
    guicontact.setEmails(contactDto.getEmails().stream().collect(Collectors.joining(", ")));
    guicontact.setGithubId(contactDto.getGithubId());
    return guicontact;
  }
}
