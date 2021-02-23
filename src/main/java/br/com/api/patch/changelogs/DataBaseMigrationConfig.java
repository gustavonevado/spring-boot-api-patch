package br.com.api.patch.changelogs;

import br.com.api.patch.domain.entity.Movie;
import br.com.api.patch.gateway.repository.MovieRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;

@ChangeLog
@Slf4j
public class DataBaseMigrationConfig {

  @ChangeSet(order = "001", id = "createMovieCollection", author = "nevado")
  public void createMovieCollection(MongockTemplate db) {
    MongoCollection collection = db.createCollection("movies");

  }

  @ChangeSet(order = "002", id = "initial data", author = "nevado")
  public void loadInitialData(MovieRepository movieRepository) {
    List<Movie> movies = new ArrayList<>();
    movies.add(
        createMovie(null, Arrays.asList("Joao Silva", "Maria Silva"),
            Arrays.asList("Infantil"), "INGLATERRA", "João e Maria", 9f));
    movies.add(
        createMovie(Arrays.asList("Oscar"), Arrays.asList("Keanu Reeves", "Laurence Fishburne"),
            Arrays.asList("Ficção"), "EUA", "Matrix", 10f));
    movies.add(
        createMovie(Arrays.asList("Oscar"), Arrays.asList("ian McKellen", "Elijah Wood"),
            Arrays.asList("Ficção"), "Nova Zelandia", "O Senhor dos Anéis", 10f));

    movieRepository.insert(movies);
  }

  @ChangeSet(runAlways = true, order = "100", id = "print history database migration", author = "nevado")
  public void printDataBaseMigration(MongockTemplate db) {
    db.getCollection("mongockChangeLog").find()
        .forEach(element -> {
          log.info("=================== DataBaseMigrationHistory ===================");
          log.info(element.toString());
          log.info("=================== ======================== ===================");
        });
  }

  private Movie createMovie(List<String> awards, List<String> cast,
      List<String> category, String country, @NotBlank String name, Float score) {
    return Movie.builder()
        .awards(awards)
        .cast(cast)
        .category(category)
        .country(country)
        .name(name)
        .imdbScore(score)
        .build();
  }

}


