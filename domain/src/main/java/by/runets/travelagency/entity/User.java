package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Class that represents the entity of the user.
 *
 */

@Data
@Entity
@Table(name = "'user'")
@NoArgsConstructor
@ToString(exclude = {"reviews", "tours"})
@EqualsAndHashCode(exclude = {"reviews", "tours"})
public class User {
	@Id
	@Column(name = "u_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	/**
	 * This is a field which represents a user login.
	 */
	private String login;
	/**
	 * This is a field which represents a user password.
	 */
	private String password;
	/**
	 * This is a field which represents a user review of tour.
	 */
	@OneToMany(mappedBy = "'user'")
	private List<Review> reviews;
	@ManyToMany(mappedBy = "users")
	private List<Tour> tours;
}
