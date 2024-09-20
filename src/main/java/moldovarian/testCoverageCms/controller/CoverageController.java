package moldovarian.testCoverageCms.controller;

import lombok.extern.log4j.Log4j2;
import moldovarian.testCoverageCms.entity.*;
import moldovarian.testCoverageCms.repository.ServiceRepository;
import moldovarian.testCoverageCms.repository.TeamRepository;
import moldovarian.testCoverageCms.service.EndpointService;
import moldovarian.testCoverageCms.service.ParameterService;
import moldovarian.testCoverageCms.service.ServiceService;
import moldovarian.testCoverageCms.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Log4j2
@RestController
@RequestMapping("/coverage")
public class CoverageController {

    private final TeamService teamService;
    private final ServiceService serviceService;
    private final EndpointService endpointService;
    private final ParameterService parameterService;

    private CoverageController(
            TeamService teamService, ServiceService serviceService, EndpointService endpointService,
            ParameterService parameterService
    ) {
        this.teamService = teamService;
        this.serviceService = serviceService;
        this.endpointService = endpointService;
        this.parameterService = parameterService;
    }

    @GetMapping("/team/{requestedId}")
    private ResponseEntity<Team> getTeamById(@PathVariable("requestedId") Long requestedId) {
        var team = teamService.getById(requestedId);
        return team != null ? ResponseEntity.ok(team) : ResponseEntity.notFound().build();
    }

    @GetMapping("/teams")
    private ResponseEntity<List<Team>> getTeams() {
        return ResponseEntity.ok(teamService.getAll());
    }

    @PostMapping("/team")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) throws URISyntaxException {
        Team result = teamService.save(team);
        return ResponseEntity.created(new URI("/api/coverage/team/" + result.getId()))
                .body(result);
    }

    @PutMapping("/team/{requestedId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Team> updateTeam(@PathVariable("requestedId") Long requestedId, @RequestBody Team team) {
        return ResponseEntity.ok().body(teamService.update(requestedId, team));
    }

    @DeleteMapping("/team/{requestedId}")
    public ResponseEntity<?> deleteTeam(@PathVariable("requestedId") Long requestedId) {
        teamService.deleteById(requestedId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/service/{requestedId}")
    private ResponseEntity<Service> getServiceById(@PathVariable("requestedId") Long requestedId) {
        var service = serviceService.getById(requestedId);
        return service != null ? ResponseEntity.ok(service) : ResponseEntity.notFound().build();
    }

    @GetMapping("/services/{team}")
    private ResponseEntity<List<Service>> getServicesByTeam(@PathVariable("team") String team) {
        return ResponseEntity.ok(serviceService.getListByTeamName(team));
    }

    @GetMapping("/services")
    private ResponseEntity<List<Service>> getServices() {
        return ResponseEntity.ok(serviceService.getAll());
    }

    @PostMapping("/service")
    public ResponseEntity<Service> createService(@RequestBody Service service) throws URISyntaxException {
        Service result = serviceService.save(service);
        return ResponseEntity.created(new URI("/api/coverage/service/" + result.getId()))
                .body(result);
    }

    @PutMapping("/service/{requestedId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Service> updateService(
            @PathVariable("requestedId") Long requestedId, @RequestBody Service service
    ) {
        return ResponseEntity.ok().body(serviceService.update(requestedId, service));
    }

    @DeleteMapping("/service/{requestedId}")
    public ResponseEntity<?> deleteService(@PathVariable("requestedId") Long requestedId) {
        serviceService.deleteById(requestedId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/endpoint/{requestedId}")
    private ResponseEntity<Endpoint> getEndpointById(@PathVariable("requestedId") Long requestedId) {
        var endpoint = endpointService.getById(requestedId);
        return endpoint != null ? ResponseEntity.ok(endpoint) : ResponseEntity.notFound().build();
    }

    @GetMapping("/endpoints/{service}")
    private ResponseEntity<List<Endpoint>> getEndpointsByService(@PathVariable("service") String service) {
        return ResponseEntity.ok(endpointService.getListByServiceName(service));
    }

    @GetMapping("/endpoints")
    private ResponseEntity<List<Endpoint>> getEndpoints() {
        return ResponseEntity.ok(endpointService.getAll());
    }

    @PostMapping("/endpoint")
    public ResponseEntity<Endpoint> createEndpoint(@RequestBody Endpoint endpoint) throws URISyntaxException {
        Endpoint result = endpointService.save(endpoint);
        return ResponseEntity.created(new URI("/api/coverage/service/" + result.getId()))
                .body(result);
    }

    @PutMapping("/endpoint/{requestedId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Endpoint> updateEndpoint(
            @PathVariable("requestedId") Long requestedId, @RequestBody Endpoint endpoint
    ) {
        return ResponseEntity.ok().body(endpointService.update(requestedId, endpoint));
    }

    @DeleteMapping("/endpoint/{requestedId}")
    public ResponseEntity<?> deleteEndpoint(@PathVariable("requestedId") Long requestedId) {
        endpointService.deleteById(requestedId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/parameter/{requestedId}")
    private ResponseEntity<Parameter> getParameterById(@PathVariable("requestedId") Long requestedId) {
        var parameter = parameterService.getById(requestedId);
        return parameter != null ? ResponseEntity.ok(parameter) : ResponseEntity.notFound().build();
    }

    @GetMapping("/parameters/{endpoint}")
    private ResponseEntity<List<Parameter>> getParametersByTeam(@PathVariable("endpoint") String endpoint) {
        return ResponseEntity.ok(parameterService.getListByEndpointName(endpoint));
    }

    @GetMapping("/parameters")
    private ResponseEntity<List<Parameter>> getParameters() {
        return ResponseEntity.ok(parameterService.getAll());
    }

    @PostMapping("/parameter")
    public ResponseEntity<Parameter> createParameter(@RequestBody Parameter parameter) throws URISyntaxException {
        Parameter result = parameterService.save(parameter);
        return ResponseEntity.created(new URI("/api/coverage/parameter/" + result.getId()))
                .body(result);
    }

    @PutMapping("/parameter/{requestedId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Parameter> updateParameter(
            @PathVariable("requestedId") Long requestedId, @RequestBody Parameter parameter
    ) {
        return ResponseEntity.ok().body(parameterService.update(requestedId, parameter));
    }

    @DeleteMapping("/parameter/{requestedId}")
    public ResponseEntity<?> deleteParameter(@PathVariable("requestedId") Long requestedId) {
        parameterService.deleteById(requestedId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    private ResponseEntity<List<Coverage>> getCoverage() {
        double coverage;
        List<Coverage> coverages = new ArrayList<>();
        List<Team> teams = teamService.getAll();
        for (Team team : teams) {
            List<Service> services = serviceService.getListByTeamName(team.getName());
            for (Service service : services) {
                List<Endpoint> endpoints = endpointService.getListByServiceName(service.getName());
                for (Endpoint endpoint : endpoints) {
                    List<Parameter> parameters = parameterService.getListByEndpointName(endpoint.getName());
                    long parametersCount = parameters.size();
                    log.info("parametersCount: " + parametersCount);
                    long coveredParametersCount = parameters.stream()
                            .filter(parameter -> parameter.getIsCovered().equals(true))
                            .count();
                    log.info("coveredParametersCount: " + coveredParametersCount);
                    coverage = ((double) coveredParametersCount / parametersCount) * 100;
                    log.info("coverage: " + coverage + "%");
                    coverages.add(new Coverage(team.getName(), service.getName(), endpoint.getName(), coverage));
                }
            }
        }
        return ResponseEntity.ok(coverages);
    }
}
