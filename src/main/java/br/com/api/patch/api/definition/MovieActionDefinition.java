package br.com.api.patch.api.definition;

import br.com.api.patch.domain.dto.MovieDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.json.JsonMergePatch;
import javax.json.JsonPatch;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "Movie Cast actions", hidden = true)
public interface MovieActionDefinition {


  @ApiOperation(value = "Cria filme")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Sucesso na atualização ."),
      @ApiResponse(code = 500, message = "Erro interno")})
  @PostMapping(value = "/movies/actions")
  public MovieDTO createMovie(
      @Valid @RequestBody MovieDTO request);

  @ApiOperation(value = "Update filme")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Sucesso na atualização ."),
      @ApiResponse(code = 500, message = "Erro interno")})
  @PutMapping(value = "/movies/actions")
  public MovieDTO updateMovie(
      @Valid @RequestBody MovieDTO request);

  @ApiOperation(value = "Atualiza Patch filme - abordagem MERGE")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Sucesso na atualização."),
      @ApiResponse(code = 500, message = "Erro interno")})
  @PatchMapping(value = "/movies/actions/merge/{name}", consumes = "application/merge-patch+json")
  public MovieDTO patchMovieMerge(
      @ApiParam(value = "Nome filme", example = "007") @PathVariable String name,
      @RequestBody JsonMergePatch request);

  @ApiOperation(value = "Atualiza Patch filme - abordagem Json Patch")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Sucesso na atualização."),
      @ApiResponse(code = 500, message = "Erro interno")})
  @PatchMapping(value = "/movies/actions/json/{name}", consumes = "application/json-patch+json")
  public MovieDTO patchMovieJson(
      @ApiParam(value = "Nome filme", example = "007") @PathVariable String name,
      @RequestBody JsonPatch request);

  @ApiOperation(value = "Remoção Filme")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Sucesso na remoção."),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 500, message = "Erro interno")})
  @DeleteMapping(value = "/movies/actions/{name}")
  public void removeMovie(
      @ApiParam(value = "Filme a ser deletada", required = true) @PathVariable(value = "name") String name);

}

