package tn.esprit.zaiem_nesrine_arctic10.services;
import java.util.Set;
import tn.esprit.zaiem_nesrine_arctic10.entities.Calls;
import tn.esprit.zaiem_nesrine_arctic10.entities.CallSkills;
import tn.esprit.zaiem_nesrine_arctic10.entities.CallStatus;
import tn.esprit.zaiem_nesrine_arctic10.entities.Calls;



import tn.esprit.zaiem_nesrine_arctic10.entities.Calls;
import java.util.List;
public interface ICallsServices {
    Calls addCall(Calls call);
    Calls updateCall(Calls call);
    void deleteById(long callId);
    Calls getById(long callId);
    List<Calls> getAll();
    Calls assignedToAgent (long callId, long agentId);
    Calls assignedToAgent (Calls call, long agentId);
    Calls assignCallToAISystem(Long callId, Long aiSystemId);
    boolean callRequiresHumanAgent(Calls call);
    void autoAssignCallsToAgents(Set<Long> callIds);
    void assignCallsToAgents(Set<Long> callsIds);
    List<Calls> findByStatusAndAssignedAgent_AgentsId(CallStatus status, long agentId);
    List<Calls> findByStatus(CallStatus status);
    List<Calls> findByAssignedAgentIsNull();
    List<Calls> findByRequiredSkillsContains(CallSkills skill);
    List<Calls> findTop5ByRequiredSkillsContainsOrderByCallsDateTimeAsc(CallSkills skill);
    boolean existsByPhoneNumber(String phoneNumber);
    long countByStatus(CallStatus status);
    List<Calls> findCallsByAgent(Long idAgent);
    List<Calls> findCallsBySkill(CallSkills skill);
    List<Object[]> countCallsByStatus();
    List<Object[]> findTopActiveAgents();
    List<Calls> findTodayCalls();
    List<Calls> getCallsByProjectLibelle(String libelle);
}