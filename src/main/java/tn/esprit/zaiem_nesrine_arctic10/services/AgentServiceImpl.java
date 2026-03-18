package tn.esprit.zaiem_nesrine_arctic10.services;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.zaiem_nesrine_arctic10.entities.Agent;
import tn.esprit.zaiem_nesrine_arctic10.entities.Project;
import tn.esprit.zaiem_nesrine_arctic10.repositories.IAgentRepository;
import tn.esprit.zaiem_nesrine_arctic10.repositories.IProjectRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AgentServiceImpl implements IAgentService {

    private final IAgentRepository agentRepository;
    private final IProjectRepository ProjectRepository;

    @Override
    public Agent addAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public Agent updateAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public void deleteById(long agentId) {
        agentRepository.deleteById(agentId);
    }

    @Override
    public void deleteAgent(Agent agent) {
        agentRepository.delete(agent);
    }

    @Override
    public Agent getById(long agentId) {
        return agentRepository.findById(agentId).orElseThrow(()-> new EntityNotFoundException("Calls with id " + agentId + " not found"));
    }

    @Override
    public List<Agent> getAll() {
        return agentRepository.findAll();
    }

    @Override
    public Agent addAndAssignToProjects(Agent agents) {
        Agent newAgents = agentRepository.save(agents);
        for(Project aProject : agents.getProjects()){
            aProject.getAgents().add(newAgents);
            ProjectRepository.save(aProject);
        }
        return newAgents;
    }
}