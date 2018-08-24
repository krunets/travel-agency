package by.runets.travelagency.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/** Class that represents the entity of the country. */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(schema = "TRAVEL_AGENCY", name = "country")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"hotels", "tours"})
@EqualsAndHashCode(exclude = {"hotels", "tours"})
@NamedQueries(@NamedQuery(name = "FIND_COUNTRY_BY_NAME", query = "from Country c WHERE c.name=:countryName"))
public class Country {
  @Id
  @Column(name = "c_id")
  @Min(value = 0)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
  @SequenceGenerator(
    name = "country_generator",
    sequenceName = "country_sequence",
    allocationSize = 4
  )
  private long id;
  /** This is a field which represents a Country short name by ISO_3166. */
  @NotNull
  @Size(min = 2, max = 2)
  @Column(name = "c_name")
  private String name;
  /** This is a field which represents a list of hotels. */
  @OneToMany(mappedBy = "country")
  @BatchSize(size = 5)
  private List<Hotel> hotels = new ArrayList<>();
  /** This is a field which represents a list of tours in exist country. */
  @ManyToMany(mappedBy = "countries")
  private List<Tour> tours = new ArrayList<>();
}
