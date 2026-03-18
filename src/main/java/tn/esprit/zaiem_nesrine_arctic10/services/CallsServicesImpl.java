package tn.esprit.zaiem_nesrine_arctic10.services;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.zaiem_nesrine_arctic10.entities.*;
import tn.esprit.zaiem_nesrine_arctic10.repositories.IAISystemRepository;
import tn.esprit.zaiem_nesrine_arctic10.repositories.IAgentRepository;
import tn.esprit.zaiem_nesrine_arctic10.repositories.ICallsRepository;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CallsServicesImpl implements ICallsServices{

    private final ICallsRepository callsRepository;
    private final IAgentRepository agentRepository;

//    @Autowired
//    public void setCallsRepository(ICallsRepository callsRepository) {
//        this.callsRepository = callsRepository;
//    }

    @Override
    public Calls addCall(Calls call) {
        call.setCallsDateTime(java.time.LocalDateTime.now());
        call.setStatus(CallStatus.ON_HOLD);
        return callsRepository.save(call);
    }

    @Override
    public Calls updateCall(Calls call) {
        return callsRepository.save(call);
    }

    @Override
    public void deleteById(long id) {
        callsRepository.deleteById(id);
    }

    @Override
    public Calls getById(long id) {
        return callsRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Calls with id " + id + " not found"));
    }

    @Override
    public List<Calls> getAll() {
        return callsRepository.findAll();
    }

    @Override

    public Calls assignedToAgent(long callId, long agentId) {
        Calls call = callsRepository.findById(callId).orElseThrow(()->new EntityNotFoundException("call with the id "+callId+" not found"));
        Agent agent = agentRepository.findById(agentId).orElseThrow(()->new EntityNotFoundException("agent with the id "+agentId+" not found"));

        if(!agent.isAvailable()){
            throw new IllegalStateException("agent with the id "+agentId+" is not available");
        }

        call.setAssignedAgent(agent);
        agent.setAvailable(false);
        call.setStatus(CallStatus.IN_PROGRESS);
        agentRepository.save(agent);

        return callsRepository.save(call);
    }

    @Override
    public Calls assignedToAgent(Calls call, long agentId) {
        Agent agent = agentRepository.findById(agentId).orElseThrow(()->new EntityNotFoundException("agent with the id "+agentId+" not found"));
        call.setAssignedAgent(agent);
        return callsRepository.save(call);
    }
    private final IAISystemRepository aiSystemRepository;

    @Override
    public Calls assignCallToAISystem(Long callId, Long aiSystemId) {
        Calls call = callsRepository.findById(callId).orElseThrow(() -> new EntityNotFoundException("call with the id " + callId + " not found"));
        AISystem aiSystem = aiSystemRepository.findById(aiSystemId).orElseThrow(() -> new EntityNotFoundException("AI system with the id " + aiSystemId + " not found"));

        if (!aiSystem.isAvailable()) {
            throw new IllegalStateException("AI system with the id " + aiSystemId + " is not available");
        }

        if (aiSystem.getCalls().size() >= 2) {
            throw new IllegalStateException("AI system with the id " + aiSystemId + " cannot handle more than 2 calls at a time");
        }

        call.setAssignedAiSystem(aiSystem);
        aiSystem.getCalls().add(call);

        if (aiSystem.getCalls().size() >= 2) {
            aiSystem.setAvailable(false);
        }

        aiSystemRepository.save(aiSystem);
        return callsRepository.save(call);
    }
    @Override
    public boolean callRequiresHumanAgent(Calls call) {
        return call.getRequiredSkills().contains(CallSkills.Technical_support);
    }
    @Override
    public void autoAssignCallsToAgents(Set<Long> callIds) {
        List<Agent> availableAgents = agentRepository.findByAvailableTrue();

        for (Long callId : callIds) {
            Calls call = callsRepository.findById(callId).orElse(null);

            if (call != null && callRequiresHumanAgent(call)) {
                for (Agent agent : availableAgents) {
                    boolean hasMatchingSkill = call.getRequiredSkills().stream()
                            .anyMatch(skill -> agent.getSkills().contains(skill));

                    if (hasMatchingSkill) {
                        call.setAssignedAgent(agent);
                        call.setStatus(CallStatus.IN_PROGRESS);
                        agent.setAvailable(false);
                        agentRepository.save(agent);
                        callsRepository.save(call);
                        break;
                    }
                }
            }
        }
    }
    @Override
    public void assignCallsToAgents(Set<Long> callsIds) {
        for (Long callId : callsIds) {
            Calls call = callsRepository.findById(callId).orElse(null);

            if (call != null) {
                if (callRequiresHumanAgent(call)) {
                    List<Agent> availableAgents = agentRepository.findByAvailableTrue();
                    for (Agent agent : availableAgents) {
                        boolean isCompetent = call.getRequiredSkills().stream()
                                .anyMatch(skill -> agent.getSkills().contains(skill));

                        if (isCompetent) {
                            call.setAssignedAgent(agent);
                            call.setStatus(CallStatus.IN_PROGRESS);
                            agent.setAvailable(false);
                            agentRepository.save(agent);
                            callsRepository.save(call);
                            break;
                        }
                    }
                }

            }
        }
    }
}