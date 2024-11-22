package javarush.com.ua.controller;

import javarush.com.ua.dto.UserDto;
import javarush.com.ua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/public/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public Mono<ResponseEntity<String>> createUser(@RequestBody UserDto userDto) {
    return userService
        .createUser(userDto)
        .map(user -> ResponseEntity.ok("User created successfully"))
        .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(e.getMessage())));
  }
}
