package javarush.com.ua.controller;

import javarush.com.ua.dto.ContactDto;
import javarush.com.ua.entity.ContactEntity;
import javarush.com.ua.repository.ContactDtoRepository;
import javarush.com.ua.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactDtoRepository contactDtoRepository;

    private final ContactService contactService;

    private final ModelMapper modelMapper;


    @GetMapping
    public Flux<ContactDto> getAllContats(@AuthenticationPrincipal Mono<UserDetails> currentUser) {    return currentUser
            .map(UserDetails::getUsername)
            .flatMapMany(contactDtoRepository::findAllByUser)
            .map(this::convertToContactDto);
    }
    @PostMapping
    public Mono<ContactDto> createContact(@AuthenticationPrincipal Mono<UserDetails> currentUser,
                                       @RequestBody ContactDto contact) {
        return currentUser
                .map(UserDetails::getUsername)
                .flatMap(username -> {
                    var entity= convertToContactEntity(contact);
                    entity.setUser(username);
                    return contactDtoRepository.save(entity)
                            .map(this::convertToContactDto);
                });
    }

    @GetMapping("/{id}")
    public Mono<ContactDto> getContactById(@PathVariable String id) {
        return contactDtoRepository.findById(id)
                .map(this::convertToContactDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delContactById(@PathVariable String id) {
        return contactDtoRepository.deleteById(id);
    }

    @GetMapping("/search")
    public Flux<ContactDto> searchContacts(@RequestParam String search) {
        return contactService.searchContacts(search)
                .map(this::convertToContactDto);
    }

    private ContactDto convertToContactDto(ContactEntity deviceEntity) {
        return modelMapper.map(deviceEntity, ContactDto.class);
    }
    private ContactEntity convertToContactEntity(ContactDto deviceEntity) {
        return modelMapper.map(deviceEntity, ContactEntity.class);
    }
}
