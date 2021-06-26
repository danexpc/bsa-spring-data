package com.bsa.springdata.project;

import com.bsa.springdata.project.dto.CreateProjectRequestDto;
import com.bsa.springdata.project.dto.ProjectDto;
import com.bsa.springdata.project.dto.ProjectSummaryDto;
import com.bsa.springdata.team.Team;
import com.bsa.springdata.team.TeamRepository;
import com.bsa.springdata.team.Technology;
import com.bsa.springdata.team.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private TeamRepository teamRepository;

    public List<ProjectDto> findTop5ByTechnology(String technology) {
        return projectRepository.findTop5ByTechnology(technology)
                .stream()
                .map(ProjectDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<ProjectDto> findTheBiggest() {
        return projectRepository.findTheBiggest()
                .map(ProjectDto::fromEntity);
    }

    public List<ProjectSummaryDto> getSummary() {
        return projectRepository.getSummary();
    }

    public int getCountWithRole(String role) {
        return projectRepository.getCountWithRole(role);
    }

    @Transactional
    public UUID createWithTeamAndTechnology(CreateProjectRequestDto createProjectRequest) {
        var technology = technologyRepository.save(
                Technology.builder()
                        .name(createProjectRequest.getTech())
                        .description(createProjectRequest.getTechDescription())
                        .link(createProjectRequest.getTechLink())
                        .build()
        );

        var project = projectRepository.save(
                Project.builder()
                        .name(createProjectRequest.getProjectName())
                        .description(createProjectRequest.getProjectDescription())
                        .build()
        );

        teamRepository.save(
                Team.builder()
                        .area(createProjectRequest.getTeamArea())
                        .name(createProjectRequest.getTeamName())
                        .room(createProjectRequest.getTeamRoom())
                        .technology(technology)
                        .project(project)
                        .build()
        );

        return project.getId();
    }
}
