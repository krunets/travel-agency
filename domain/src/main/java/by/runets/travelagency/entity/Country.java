package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Country<K> extends Entity <K> {
	private String name;
	
	public Country (K id, String name) {
		super(id);
		this.name = name;
	}
	
}
