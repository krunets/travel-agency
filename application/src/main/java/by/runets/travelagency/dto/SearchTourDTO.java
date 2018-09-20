package by.runets.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchTourDTO {
  private static final String REGEX = "[0-9]+";
  @NonNull
  private String countryName;
  @NonNull
  private String startTourDate;
  @NonNull
  @Pattern(regexp = REGEX)
  private long tourDuration;
}
