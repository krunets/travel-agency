package by.runets.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CountryListDTO {
  private List<CountryDTO> suggestions;
}
