package by.runets.travelagency.entity;

import by.runets.travelagency.converter.RoleConverter;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

import static by.runets.travelagency.util.constant.NamedQueryConstant.*;

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
@NamedQueries({
		@NamedQuery(name = FIND_BY_LOGIN, query = FIND_BY_LOGIN_NAMED_QUERY),
		@NamedQuery(name = FIND_BY_ROLE, query = FIND_BY_ROLE_NAMED_QUERY)})
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
	@Convert(converter = RoleConverter.class)
	private Role role;
}
