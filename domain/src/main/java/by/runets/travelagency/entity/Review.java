package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Class that represents the entity of the review.
 *
 */
@Data
@Entity
@Table(name = "review")
@NoArgsConstructor
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
public class Review {
	@Id
	@Column(name = "r_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	/**
	 * This is a field which represents a content of review.
	 */
	private String content;
	/**
	 * This is a field which represents a user who left feedback about tour.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "u_id")
	private User user;
}