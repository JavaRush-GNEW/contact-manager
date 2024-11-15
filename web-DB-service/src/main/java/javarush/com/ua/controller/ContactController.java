package javarush.com.ua.controller;

import javarush.com.ua.dto.ContactDto;
import javarush.com.ua.entity.User;
import javarush.com.ua.repository.ContactDtoRepository;
import javarush.com.ua.service.ContactService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public Flux<ContactDto> getAllContats(@AuthenticationPrincipal Mono<UserDetails> currentUser) {    return currentUser
            .map(UserDetails::getUsername)
            .flatMapMany(contactDtoRepository::findAllByUser);
    }
    @PostMapping
    public Mono<ContactDto> createContact(@AuthenticationPrincipal Mono<UserDetails> currentUser,
                                       @RequestBody ContactDto contact) {
        return currentUser
                .map(UserDetails::getUsername)
                .flatMap(username -> {
                    contact.setUser(username);
                    return contactDtoRepository.save(contact);
                });
    }

    @GetMapping("/{id}")
    public Mono<ContactDto> getContactById(@PathVariable String id) {
        return contactDtoRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delContactById(@PathVariable String id) {
        return contactDtoRepository.deleteById(id);
    }

    @GetMapping("/search")
    public Flux<ContactDto> searchContacts(@RequestParam String search) {
        return contactService.searchContacts(search);
    }
}
