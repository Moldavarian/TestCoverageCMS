package moldovarian.testCoverageCms.repository;

import moldovarian.testCoverageCms.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM service s WHERE s.team_id = (SELECT t.id FROM team t WHERE t.name = :team)"
    )
    List<Service> getServicesByTeam(@Param("team") String team);
}
