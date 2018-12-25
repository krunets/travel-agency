package by.runets.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchTourDTO {
  private static final String REGEX = "[0-9]+";
  @NonNull private String countryName;
  @NonNull private String startTourDate;
  @NonNull private List<String> transferTypeCodes;

  @NonNull
  @Pattern(regexp = REGEX)
  private List<Long> tourDuration;

  private List<BigDecimal> costs;
  private List<Long> tourTypeIds;
}
