package br.com.api.patch.usecase;

import br.com.api.patch.domain.dto.MovieDTO;
import br.com.api.patch.domain.entity.Movie;
import br.com.api.patch.exceptions.NotFound;
import br.com.api.patch.gateway.repository.MovieRepository;
import br.com.api.patch.util.PatchUtils;
import java.util.Optional;
import javax.json.JsonMergePatch;
import javax.json.JsonPatch;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MovieActionCase {

  private final ModelMapper mapper;

  private MovieRepository movieRepository;

  private PatchUtils patchService;

  public MovieActionCase(ModelMapper mapper,
      MovieRepository movieRepository,
      PatchUtils patchService) {
    this.mapper = mapper;
    this.movieRepository = movieRepository;
    this.patchService = patchService;
  }

  public MovieDTO createMovie(MovieDTO request) {
    Optional<Movie> pharmacyFlags = this.movieRepository.findByName(request.getName());

    if (pharmacyFlags.isPresent()) {
      throw new NotFound("Already exists a movie with name: {}", request.getName());
    }

    Movie movieSave = mapper.map(request, Movie.class);
    final Movie movieResponse = this.movieRepository.save(movieSave);

    return mapper.map(movieResponse, MovieDTO.class);
  }

  public MovieDTO patchMergeMovie(String name, JsonMergePatch request) {

    Movie movie = movieRepository.findByName(name)
        .orElseThrow(() -> new NotFound("not found ", name));
    ;
    // Apply the patch
    Movie moviePatched = patchService.mergePatch(request, movie, Movie.class);

    return mapper.map(movieRepository.save(moviePatched), MovieDTO.class);

  }

  public MovieDTO updateMovie(MovieDTO request) {

    Optional<Movie> movie = movieRepository.findByName(request.getName());

    Movie movieToUpdate = mapper.map(request, Movie.class);

    return mapper.map(movieRepository.save(movieToUpdate), MovieDTO.class);
  }

  public void removeMovie(String name) {
    Optional<Movie> movie = movieRepository.findByName(name);
    if (movie.isPresent()) {
      movieRepository.delete(movie.get());
    }
  }

  public MovieDTO patchMovieJson(String name, JsonPatch patchDocument) {
    Movie movie = movieRepository.findByName(name)
        .orElseThrow(() -> new NotFound("not found ", name));
    // Apply the patch
    Movie moviePatched = patchService.patch(patchDocument, movie, Movie.class);

    return mapper.map(movieRepository.save(moviePatched), MovieDTO.class);

  }
}
