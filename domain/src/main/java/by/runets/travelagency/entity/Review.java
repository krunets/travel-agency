package by.runets.travelagency.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Class that represents the entity of the review.
 *
 */
@Data
@Entity
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
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
	@NotNull
	private String content;
	/**
	 * This is a field which represents a user who left feedback about tour.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private User user;
}