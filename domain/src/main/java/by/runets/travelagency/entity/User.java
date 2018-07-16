package by.runets.travelagency.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Class that represents the entity of the user.
 *
 */

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
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
	@NotNull
	private String login;
	/**
	 * This is a field which represents a user password.
	 */
	@NotNull
	private String password;
	/**
	 * This is a field which represents a user review of tour.
	 */
	@OneToMany(mappedBy = "user")
	private List<Review> reviews;
	@ManyToMany(mappedBy = "users")
	private List<Tour> tours;
}
