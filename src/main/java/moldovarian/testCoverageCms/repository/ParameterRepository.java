package moldovarian.testCoverageCms.repository;

import moldovarian.testCoverageCms.entity.Endpoint;
import moldovarian.testCoverageCms.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM parameter p WHERE p.endpoint_id = (SELECT e.id FROM endpoint e WHERE e.name = :endpoint)"
    )
    List<Parameter> getParametersByEndpoint(@Param("endpoint") String endpoint);
}
