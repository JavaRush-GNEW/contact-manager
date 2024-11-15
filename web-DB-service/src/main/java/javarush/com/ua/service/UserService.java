package javarush.com.ua.service;

import javarush.com.ua.dto.UserDto;
import javarush.com.ua.entity.User;
import javarush.com.ua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Mono<User> saveUser(String username, String password, String... roles) {
        User user = new User(username, passwordEncoder.encode(password), Arrays.asList(roles));
        return userRepository.save(user);
    }
    public Mono<User> createUser(UserDto userDto) {
        // Перевіряємо, чи користувач із таким ім'ям уже існує
       return userRepository.findByUsername(userDto.getUsername())
                .flatMap(existingUser -> Mono.<User>error(new IllegalArgumentException("User already exists")))
                .switchIfEmpty(Mono.defer(() -> {
                    User newUser = new User(
                            userDto.getUsername(),
                            passwordEncoder.encode(userDto.getPassword()),
                            userDto.getRoles()
                    );
                    return userRepository.save(newUser);
                }));
    }
}
