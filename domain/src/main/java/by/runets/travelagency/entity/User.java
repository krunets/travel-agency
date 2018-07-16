package by.runets.travelagency.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Class that represents the entity of the user.
 *
 */

@Data
@Entity
@DynamicInsert
@DynamicUpdate
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
	@BatchSize(size = 5)
	private List<Review> reviews;
	@ManyToMany(mappedBy = "users")
	@BatchSize(size = 5)
	private List<Tour> tours;
}
