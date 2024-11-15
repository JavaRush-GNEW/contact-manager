package javarush.com.ua.repository;

import javarush.com.ua.dto.ContactDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContactDtoRepository extends ReactiveMongoRepository<ContactDto,String> {

    Flux<ContactDto> findAllByUser(String user);
    @Override
    Mono<Void> deleteById(String id);
}
