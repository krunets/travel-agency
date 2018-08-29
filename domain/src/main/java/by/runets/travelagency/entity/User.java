package by.runets.travelagency.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Class that represents the entity of the user.
 */

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(schema = "travel_agency", name = "user")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"reviews", "tours"})
@EqualsAndHashCode(exclude = {"reviews", "tours"})
@NamedQueries(@NamedQuery(
		name = "FIND_BY_LOGIN", query = "from User u WHERE u.login=:login"
))
public class User {
	@Id
	@Column(name = "u_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
