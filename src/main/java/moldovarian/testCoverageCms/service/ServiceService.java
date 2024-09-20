package moldovarian.testCoverageCms.service;

import lombok.RequiredArgsConstructor;
import moldovarian.testCoverageCms.entity.Service;
import moldovarian.testCoverageCms.entity.Team;
import moldovarian.testCoverageCms.repository.ServiceRepository;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public List<Service> getAll() {
        return serviceRepository.findAll();
    }

    public Service getById(Long id) {
        var service = serviceRepository.findById(id);
        return service.orElse(null);
    }

    public List<Service> getListByTeamName(String teamName) {
        return serviceRepository.getServicesByTeam(teamName);
    }

    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    public Service update(Long id, Service service) {
        var before = getById(id);
        if (before != null) {
            before.setName(service.getName());
            return before;
        }
        return null;
    }

    public void deleteById(Long id) {
        serviceRepository.delete(getById(id));
    }
}
