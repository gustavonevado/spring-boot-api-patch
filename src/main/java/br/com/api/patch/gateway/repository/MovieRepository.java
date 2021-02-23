package br.com.api.patch.gateway.repository;

import br.com.api.patch.domain.entity.Movie;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {

  Optional<Movie> findByName(String name);
}
