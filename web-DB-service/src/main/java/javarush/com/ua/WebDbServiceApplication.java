package javarush.com.ua;

import javarush.com.ua.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class WebDbServiceApplication {
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(WebDbServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener() {
        return applicationReadyEvent -> {
            log.info("[BOOT SERVER] {} ", new Date());

//                log.info(userService.saveUser("user","12345","USER").block().toString());
        };
    }

}
