package br.com.api.patch.api.definition;

import br.com.api.patch.domain.dto.MovieDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;

public interface MovieDefinition {

  @ApiOperation(value = "pesquisa filme")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Sucesso."),
      @ApiResponse(code = 500, message = "Erro interno")})
  @PostMapping(value = "/movies")
  public List<MovieDTO> findMovies();

}
