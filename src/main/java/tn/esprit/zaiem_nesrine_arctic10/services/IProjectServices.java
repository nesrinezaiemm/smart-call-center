package tn.esprit.zaiem_nesrine_arctic10.services;

import tn.esprit.zaiem_nesrine_arctic10.dto.ProjectsDTO;
import tn.esprit.zaiem_nesrine_arctic10.entities.Agent;
import tn.esprit.zaiem_nesrine_arctic10.entities.Calls;
import tn.esprit.zaiem_nesrine_arctic10.entities.Project;
import java.util.List;

public interface IProjectServices {

    Project addProject(Project project);
    Project updateProject(Project project);
    void deleteProjectById(long id);
    void deleteProject(Project project);
    Project getProjectById(long id);
    List<Project> getAll();
    List<Agent> getAgents(long projectId);
    Project assignToAgents(Long projectId, Long agentsId);
    ProjectsDTO getProjectsDetails(Project projects);
    ProjectsDTO findProjects(long projectId);
    Double getSumProjectByAgent(String agentName);
    void sumProjectsByAgents();
}

