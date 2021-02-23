package br.com.api.patch.domain.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class MovieDTO implements Serializable {

  private String id;

  private String name;

  private Float imdbScore;

  private List<String> category;

  private String country;

  private List<String> cast;

  private List<String> awards;

}
