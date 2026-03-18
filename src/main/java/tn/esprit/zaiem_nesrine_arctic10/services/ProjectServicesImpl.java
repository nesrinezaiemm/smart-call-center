package tn.esprit.zaiem_nesrine_arctic10.services;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.zaiem_nesrine_arctic10.entities.Agent;
import tn.esprit.zaiem_nesrine_arctic10.entities.Project;
import tn.esprit.zaiem_nesrine_arctic10.repositories.IProjectRepository;
import java.util.List;
import tn.esprit.zaiem_nesrine_arctic10.entities.Calls;
import tn.esprit.zaiem_nesrine_arctic10.entities.Agent; // not Agents
import tn.esprit.zaiem_nesrine_arctic10.repositories.ICallsRepository;
import tn.esprit.zaiem_nesrine_arctic10.repositories.IAgentRepository;
@Service
@RequiredArgsConstructor
public class ProjectServicesImpl implements IProjectServices{

    private final IProjectRepository projectRepository;

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProjectById(long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    @Override
    public Project getProjectById(long id) {
        return projectRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("project with id " + id + " not found"));
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<Agent> getAgents(long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(()->new EntityNotFoundException("Project with id "+projectId+" not found"));
        return project.getAgents().stream().toList();
    }
    private final IAgentRepository agentRepository;
    private final ICallsRepository callsRepository;
    @Override
    public Project assignToAgents(Long projectsId, Long agentsId) {
        Project project = projectRepository.findById(projectsId).orElseThrow();
        Agent agent = agentRepository.findById(agentsId).orElse(null);
        //affectation
        project.getAgents().add(agent);
        return projectRepository.save(project);

    }

}