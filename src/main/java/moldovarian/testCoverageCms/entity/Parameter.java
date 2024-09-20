package moldovarian.testCoverageCms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "parameter")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "parameter_type")
    private String parameterType;
    private String type;
    @Column(name = "is_covered")
    private Boolean isCovered;
    @Column(name = "endpoint_id")
    private Long endpointId;
}
