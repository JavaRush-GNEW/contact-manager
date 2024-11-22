package javarush.com.ua.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import javarush.com.ua.entity.Counter;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SequenceGeneratorService {

  private final ReactiveMongoTemplate mongoTemplate;

  public SequenceGeneratorService(ReactiveMongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public Mono<Long> generateSequence(String seqName) {
    return mongoTemplate
        .findAndModify(
            new Query(where("_id").is(seqName)),
            new Update().inc("sequence", 1),
            options().returnNew(true).upsert(true),
            Counter.class)
        .map(Counter::getSequence);
  }
}
