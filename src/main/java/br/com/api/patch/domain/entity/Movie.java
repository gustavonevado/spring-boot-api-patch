package br.com.api.patch.domain.entity;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Document(collection = "movies")
public class Movie {

  @Id
  private String id;

  @NotBlank
  private String name;

  private Float imdbScore;

  private List<String> category;

  private String country;

  private List<String> cast;

  private List<String> awards;

}
