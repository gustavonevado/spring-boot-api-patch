package br.com.api.patch.api.impl;

import br.com.api.patch.api.definition.MovieActionDefinition;
import br.com.api.patch.domain.dto.MovieDTO;
import br.com.api.patch.usecase.MovieActionCase;
import javax.json.JsonMergePatch;
import javax.json.JsonPatch;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieActionController implements MovieActionDefinition {

  @Autowired
  private MovieActionCase movieActionCase;

  @ResponseStatus(HttpStatus.OK)
  public MovieDTO createMovie(MovieDTO request) {
    request.setId(null);
    return this.movieActionCase.createMovie(request);
  }

  @ResponseStatus(HttpStatus.OK)
  public MovieDTO updateMovie(@Valid MovieDTO request) {
    return this.movieActionCase.updateMovie(request);
  }

  @ResponseStatus(HttpStatus.OK)
  public MovieDTO patchMovieMerge(String name, JsonMergePatch request) {
    return this.movieActionCase.patchMergeMovie(name, request);
  }


  public MovieDTO patchMovieJson(@PathVariable String name, JsonPatch patchDocument) {
    return this.movieActionCase.patchMovieJson(name, patchDocument);
  }

  @ResponseStatus(HttpStatus.OK)
  public void removeMovie(String name) {
    this.movieActionCase.removeMovie(name);
  }


}