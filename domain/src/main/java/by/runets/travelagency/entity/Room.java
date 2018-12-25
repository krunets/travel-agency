package by.runets.travelagency.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;

import static by.runets.travelagency.util.constant.NamedQueryConstant.FIND_ACTIVE_ROOM;
import static by.runets.travelagency.util.constant.NamedQueryConstant.FIND_ACTIVE_ROOM_NAMED_QUERY;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(schema = "travel_agency", name = "room")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"hotel", "user"})
@EqualsAndHashCode(exclude = {"hotel", "user"})
@NamedQueries({@NamedQuery(name = FIND_ACTIVE_ROOM, query = FIND_ACTIVE_ROOM_NAMED_QUERY)})
public class Room {
  @Id
  @Min(value = 0)
  @NonNull
  @Column(name = "room_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_sequence_generator")
  @SequenceGenerator(
    name = "room_sequence_generator",
    sequenceName = "room_sequence",
    schema = "travel_agency",
    allocationSize = 1
  )
  private long id;

  @NonNull
  @Min(value = 0)
  @Column(name = "beds")
  private int beds;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "hotel")
  private Hotel hotel;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;
}
