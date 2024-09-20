package moldovarian.testCoverageCms.service;

import lombok.RequiredArgsConstructor;
import moldovarian.testCoverageCms.entity.Endpoint;
import moldovarian.testCoverageCms.entity.Parameter;
import moldovarian.testCoverageCms.repository.EndpointRepository;
import moldovarian.testCoverageCms.repository.ParameterRepository;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ParameterService {
    private final ParameterRepository parameterRepository;

    public List<Parameter> getAll() {
        return parameterRepository.findAll();
    }

    public Parameter getById(Long id) {
        var endpoint = parameterRepository.findById(id);
        return endpoint.orElse(null);
    }

    public List<Parameter> getListByEndpointName(String endpoint) {
        return parameterRepository.getParametersByEndpoint(endpoint);
    }

    public Parameter save(Parameter parameter) {
        return parameterRepository.save(parameter);
    }

    public Parameter update(Long id, Parameter parameter) {
        var before = getById(id);
        if (before != null) {
            before.setName(parameter.getName());
            return before;
        }
        return null;
    }

    public void deleteById(Long id) {
        parameterRepository.delete(getById(id));
    }
}
