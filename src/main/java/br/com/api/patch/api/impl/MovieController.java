package br.com.api.patch.api.impl;

import br.com.api.patch.api.definition.MovieDefinition;
import br.com.api.patch.domain.dto.MovieDTO;
import br.com.api.patch.usecase.MovieCase;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Movie Controller", hidden = true)
public class MovieController implements MovieDefinition {

  private final MovieCase movieCase;

  public MovieController(MovieCase movieCase) {
    this.movieCase = movieCase;
  }

  public List<MovieDTO> findMovies() {
    return movieCase.findMovies();
  }

}