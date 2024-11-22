package javarush.com.ua.service;

import javarush.com.ua.entity.ContactEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ContactService {

  private final ReactiveMongoTemplate mongoTemplate;

  public Flux<ContactEntity> searchContacts(String searchTerm) {
    // Побудова критеріїв для пошуку по всіх полях
    Criteria criteria =
        new Criteria()
            .orOperator(
                Criteria.where("fullName").regex(searchTerm, "i"),
                Criteria.where("phones").in(searchTerm),
                Criteria.where("emails").in(searchTerm),
                Criteria.where("githubId").regex(searchTerm, "i"));

    Query query = new Query(criteria);

    return mongoTemplate.find(query, ContactEntity.class);
  }
}
