package by.runets.travelagency.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

import static by.runets.travelagency.util.constant.NamedQueryConstant.FIND_TRANSFER_TYPE_BY_CODE;
import static by.runets.travelagency.util.constant.NamedQueryConstant.FIND_TRANSFER_TYPE_BY_CODE_QUERY;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(schema = "travel_agency", name = "transfer_type")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"tours"})
@EqualsAndHashCode(exclude = {"tours"})
@NamedQueries({
  @NamedQuery(name = FIND_TRANSFER_TYPE_BY_CODE, query = FIND_TRANSFER_TYPE_BY_CODE_QUERY)
})
public class TransferType {
  @Id
  @Min(value = 0)
  @NonNull
  @Column(name = "tt_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transfer_type_sequence_generator")
  @SequenceGenerator(
    name = "transfer_type_sequence_generator",
    sequenceName = "transfer_type_sequence",
    schema = "travel_agency",
    allocationSize = 1
  )
  private long id;

  private String code;
  private String description;

  @Column(name = "price_coefficient")
  private double priceCoefficient;

  @ManyToMany(mappedBy = "transfers")
  private Set<Tour> tours;
}
