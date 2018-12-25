package by.runets.travelagency.entity;

import by.runets.travelagency.converter.RoleConverter;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static by.runets.travelagency.util.constant.NamedQueryConstant.*;

/** Class that represents the entity of the user. */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(schema = "travel_agency", name = "user")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"reviews", "hotels", "bookedRooms"})
@EqualsAndHashCode(exclude = {"reviews", "hotels", "bookedRooms"})
@NamedQueries({
  @NamedQuery(name = FIND_BY_LOGIN, query = FIND_BY_LOGIN_NAMED_QUERY),
  @NamedQuery(name = FIND_BY_ROLE, query = FIND_BY_ROLE_NAMED_QUERY),
  @NamedQuery(name = COUNT_USER, query = COUNT_USER_NAMED_QUERY),
  @NamedQuery(name = FIND_USER_WITH_HOTELS, query = FIND_USER_WITH_HOTELS_NAMED_QUERY)
})
public class User {
  @Id
  @Column(name = "u_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
  @SequenceGenerator(
    name = "user_sequence_generator",
    sequenceName = "user_sequence",
    schema = "travel_agency",
    allocationSize = 1
  )
  @NonNull
  private long id;
  /** This is a field which represents a user login. */
  @NotNull @NonNull private String login;
  /** This is a field which represents a user password. */
  @NotNull @NonNull private String password;
  /** This is a field which represents a user review of tour. */
  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  @BatchSize(size = 5)
  private Set<Review> reviews;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
    schema = "travel_agency",
    name = "hotel_m2m_user",
    joinColumns = {@JoinColumn(name = "u_id")},
    inverseJoinColumns = {@JoinColumn(name = "h_id")}
  )
  private Set<Hotel> hotels;

  @BatchSize(size = 5)
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private Set<Room> bookedRooms;

  @Enumerated(EnumType.STRING)
  @Convert(converter = RoleConverter.class)
  @NonNull
  private Role role;

  private String photo;
}
