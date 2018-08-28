package by.runets.travelagency.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PaginationDTO {
	@NotNull
	@Size(min = 10, max = 100)
	private int size;
}
