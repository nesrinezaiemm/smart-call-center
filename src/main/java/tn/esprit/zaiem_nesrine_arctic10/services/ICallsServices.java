package tn.esprit.zaiem_nesrine_arctic10.services;
import java.util.Set;
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
}