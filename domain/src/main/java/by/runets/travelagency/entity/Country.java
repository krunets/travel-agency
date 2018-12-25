package by.runets.travelagency.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static by.runets.travelagency.util.constant.NamedQueryConstant.*;

/**
 * Class that represents the entity of the country.
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(schema = "TRAVEL_AGENCY", name = "country")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"tours"})
@EqualsAndHashCode(exclude = {"tours"})
@NamedQueries({
	@NamedQuery(name = FIND_COUNTRY_BY_NAME, query = FIND_COUNTRY_BY_NAME_NAMED_QUERY),
	@NamedQuery(name = COUNT_COUNTRY, query = COUNT_COUNTRY_NAMED_QUERY)
})
public class Country {
  @Id
  @Column(name = "c_id")
  @Min(value = 0)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_sequence_generator")
  @SequenceGenerator(
	  name = "country_sequence_generator",
	  sequenceName = "country_sequence",
	  schema = "travel_agency",
	  allocationSize = 1
  )
  @NonNull
  private long id;
  /**
   * This is a field which represents a Country short name by ISO_3166.
   */
  @NonNull
  @Size(min = 2, max = 2)
  @Column(name = "c_name")
  private String name;

  /**
   * This is a field which represents a list of tours in exist country.
   */
  @ManyToMany(mappedBy = "countries")
  private List<Tour> tours = new ArrayList<>();
}
