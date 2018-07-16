package by.runets.travelagency.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/** Class that represents the entity of the country. */
@Data
@Entity
@Table(name = "country")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"hotels", "tours"})
@EqualsAndHashCode(exclude = {"hotels", "tours"})
public class Country {
  @Id
  @Column(name = "c_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
  @SequenceGenerator(
    name = "country_generator",
    sequenceName = "country_sequence",
    allocationSize = 4
  )
  private long id;
  /** This is a field which represents a Country name. */
  @NotNull
  @Column(name = "c_name")
  private String name;
  /** This is a field which represents a list of hotels. */
  @OneToMany(mappedBy = "country")
  private List<Hotel> hotels = new ArrayList<>();
  /** This is a field which represents a list of tours in exist country. */
  @ManyToMany(mappedBy = "countries")
  private List<Tour> tours = new ArrayList<>();
}
