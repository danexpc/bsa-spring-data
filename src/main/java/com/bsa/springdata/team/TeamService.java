package com.bsa.springdata.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TechnologyRepository technologyRepository;

    public void updateTechnology(int devsNumber, String oldTechnologyName, String newTechnologyName) {
        // TODO: You can use several queries here. Try to keep it as simple as possible
        List<Team> teams = teamRepository.findByTechnologyName(oldTechnologyName);
        teams.stream()
                .filter(team -> team.getUsers().size() < devsNumber)
                .forEach(team -> {
                    var technology = team.getTechnology();
                    technology.setName(newTechnologyName);
                    team.setTechnology(technology);
                });
        teamRepository.saveAll(teams);
    }

    public void normalizeName(String hipsters) {
        // TODO: Use a single query. You need to create a native query
        teamRepository.normalizeName(hipsters);
    }
}
