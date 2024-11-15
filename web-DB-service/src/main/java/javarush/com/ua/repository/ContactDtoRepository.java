package javarush.com.ua.repository;

import javarush.com.ua.entity.ContactEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContactDtoRepository extends ReactiveMongoRepository<ContactEntity,String> {

    Flux<ContactEntity> findAllByUser(String user);
    Mono<Void> deleteById(String id);
}
