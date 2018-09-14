package by.runets.travelagency.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PaginationDTO<T> {
	@Size
	private long pageAmount;
	@Size(min = 1)
	private int page;
	@Size(min = 10, max = 100)
	private int limit;
	private List<T> data;
}
