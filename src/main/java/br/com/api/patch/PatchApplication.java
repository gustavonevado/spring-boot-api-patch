package br.com.api.patch;

import br.com.api.patch.gateway.repository.MovieRepository;
import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = MovieRepository.class)
@SpringBootApplication
@EnableMongock
public class PatchApplication {

  public static void main(String[] args) {
    SpringApplication.run(PatchApplication.class, args);
  }

}
