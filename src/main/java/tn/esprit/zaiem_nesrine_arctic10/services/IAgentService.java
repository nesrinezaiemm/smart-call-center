package tn.esprit.zaiem_nesrine_arctic10.services;
import java.util.List;
import tn.esprit.zaiem_nesrine_arctic10.entities.CallSkills;
import java.util.Set;
import tn.esprit.zaiem_nesrine_arctic10.entities.Agent;
public interface IAgentService {
    Agent addAgent(Agent agent);
    Agent updateAgent(Agent agent);
    void deleteById(long agentId);
    void deleteAgent(Agent agent);
    Agent getById(long agentId);
    List<Agent> getAll();
    Agent addAndAssignToProjects(Agent agents);
    List<Agent> getAvailableAgents();
    List<Agent> getAgentsBySkill(CallSkills skill);
    List<Agent> getAvailableAgentsWithSkills(Set<CallSkills> skills);
    List<Agent> findAgentsBySkill(CallSkills skill);
    List<Agent> findMostCompetentAgentForCall(Long callsId);
}