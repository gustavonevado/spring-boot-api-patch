package br.com.api.patch.usecase;

import br.com.api.patch.domain.dto.MovieDTO;
import br.com.api.patch.gateway.repository.MovieRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class MovieCase {

    private final MovieRepository movieRepository;

    private final ModelMapper mapper;

    public MovieCase(MovieRepository movieRepository,
                              ModelMapper mapper) {
        this.movieRepository = movieRepository;
        this.mapper = mapper;
    }

    public List<MovieDTO> findMovies() {
        return movieRepository.findAll().stream()
            .map(element-> mapper.map(element,MovieDTO.class))
            .collect(Collectors.toList());
    }

}
