package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class User <K> extends Entity<K> {
	private String login;
	private String password;
	
	public User (K id, String login, String password) {
		super(id);
		this.login = login;
		this.password = password;
	}
}
