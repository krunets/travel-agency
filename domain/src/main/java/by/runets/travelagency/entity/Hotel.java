package by.runets.travelagency.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static by.runets.travelagency.util.constant.NamedQueryConstant.*;

/** Class that represents the entity of the hotel. */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(schema = "travel_agency", name = "hotel")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"tour", "users", "rooms"})
@EqualsAndHashCode(exclude = {"tour", "users", "rooms"})
@NamedQueries({
  @NamedQuery(name = FIND_ALL_HOTEL, query = FIND_ALL_HOTEL_NAMED_QUERY),
  @NamedQuery(name = COUNT_HOTEL, query = COUNT_HOTEL_NAMED_QUERY),
  @NamedQuery(name = FIND_HOTELS_BY_TOUR_ID, query = FIND_HOTELS_BY_TOUR_ID_QUERY)
})
public class Hotel {
  @Id
  @Min(value = 0)
  @Column(name = "h_id")
  @NonNull
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_sequence_generator")
  @SequenceGenerator(
    name = "hotel_sequence_generator",
    sequenceName = "hotel_sequence",
    schema = "travel_agency",
    allocationSize = 1
  )
  private long id;
  /** This is a field which represents hotel name. */
  @NotNull
  @NonNull
  @Column(name = "h_name")
  private String name;
  /** This is a field which represents a hotel phone. */
  @NotNull
  @NonNull
  @Column(name = "h_phone")
  private String phone;
  /** This is a field which represents a hotel stars rating. */
  @NotNull
  @NonNull
  @Column(name = "h_stars")
  private int stars;
  /** This is a field which represents a country. */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "tour")
  private Tour tour;

  @ManyToMany(mappedBy = "hotels", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @BatchSize(size = 5)
  private Set<User> users;

  @NonNull private double latitude;
  @NonNull private double longitude;

  @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
  @BatchSize(size = 5)
  @Column(updatable = false)
  private Set<Room> rooms;
}
