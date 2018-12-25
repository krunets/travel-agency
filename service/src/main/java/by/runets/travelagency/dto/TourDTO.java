package by.runets.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDTO {
  private long id;
  private String photo;
  @NotNull
  private String date;
  @NotNull
  private long duration;
  @NotNull
  @Size(min = 10, max = 256)
  private String description;
  @NotNull
  private long cost;
  @NotNull
  private String tourType;
  @NotNull
  private String countryName;
}
