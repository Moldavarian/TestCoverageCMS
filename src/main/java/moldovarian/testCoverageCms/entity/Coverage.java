package moldovarian.testCoverageCms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coverage {
    private String team;
    private String service;
    private String endpoint;
    private Double coveragePercent;
}
