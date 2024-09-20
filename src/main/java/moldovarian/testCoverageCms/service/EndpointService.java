package moldovarian.testCoverageCms.service;

import lombok.RequiredArgsConstructor;
import moldovarian.testCoverageCms.entity.Endpoint;
import moldovarian.testCoverageCms.repository.EndpointRepository;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class EndpointService {
    private final EndpointRepository endpointRepository;

    public List<Endpoint> getAll() {
        return endpointRepository.findAll();
    }

    public Endpoint getById(Long id) {
        var endpoint = endpointRepository.findById(id);
        return endpoint.orElse(null);
    }

    public List<Endpoint> getListByServiceName(String serviceName) {
        return endpointRepository.getEndpointsByService(serviceName);
    }

    public Endpoint save(Endpoint endpoint) {
        return endpointRepository.save(endpoint);
    }

    public Endpoint update(Long id, Endpoint endpoint) {
        var before = getById(id);
        if (before != null) {
            before.setName(endpoint.getName());
            return before;
        }
        return null;
    }

    public void deleteById(Long id) {
        endpointRepository.delete(getById(id));
    }
}
