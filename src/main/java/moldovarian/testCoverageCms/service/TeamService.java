package moldovarian.testCoverageCms.service;

import lombok.RequiredArgsConstructor;
import moldovarian.testCoverageCms.entity.Team;
import moldovarian.testCoverageCms.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    public Team getById(Long id) {
        var team = teamRepository.findById(id);
        return team.orElse(null);
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team update(Long id, Team team) {
        var before = getById(id);
        if (before != null) {
            before.setName(team.getName());
            return before;
        }
        return null;
    }

    public void deleteById(Long id) {
        teamRepository.delete(getById(id));
    }
}
