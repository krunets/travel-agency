package by.runets.travelagency.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Class that represents the entity of the review.
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(schema = "travel_agency", name = "review")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {/*"user", "tour"*/})
@EqualsAndHashCode(exclude = {"user", "tour"})
public class Review {
	@Id
	@NonNull
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
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tour")
	private Tour tour;
}