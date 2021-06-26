package com.bsa.springdata.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TechnologyRepository technologyRepository;

    @Transactional
    public void updateTechnology(int devsNumber, String oldTechnologyName, String newTechnologyName) {
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

    @Transactional
    public void normalizeName(String hipsters) {
        teamRepository.normalizeName(hipsters);
    }
}
