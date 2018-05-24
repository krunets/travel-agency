package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Review <K> extends Entity<K> {
	private String content;
	
	public Review (K id, String content) {
		super(id);
		this.content = content;
	}
}
