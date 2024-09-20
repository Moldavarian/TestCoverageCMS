package moldovarian.testCoverageCms.repository;

import moldovarian.testCoverageCms.entity.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM endpoint e WHERE e.service_id = (SELECT s.id FROM service s WHERE s.name = :service)"
    )
    List<Endpoint> getEndpointsByService(@Param("service") String service);
}
