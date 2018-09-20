package by.runets.travelagency.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ReviewDTO {
  @NotNull
  @Size(max = 255)
  private String content;
}
