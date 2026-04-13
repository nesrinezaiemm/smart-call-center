package tn.esprit.zaiem_nesrine_arctic10.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.zaiem_nesrine_arctic10.entities.CallSkills;
import tn.esprit.zaiem_nesrine_arctic10.entities.CallStatus;
import tn.esprit.zaiem_nesrine_arctic10.entities.Calls;
import tn.esprit.zaiem_nesrine_arctic10.services.ICallsServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("calls")
// http:://localhost:8082/api/calls
public class CallsRestController {
    private final ICallsServices callsServices;

    // http:://localhost:8082/api/calls/add
    @PostMapping("add")
    public Calls addCalls(@RequestBody Calls call) {
        return callsServices.addCall(call);
    }

    // http:://localhost:8082/api/calls/update
    @PutMapping("update")
    public Calls updateCalls(@RequestBody Calls call) {
        return callsServices.updateCall(call);
    }

    // http:://localhost:8082/api/calls/delete/{id}
    @DeleteMapping("delete/{id}")
    public void deleteCallsById(@PathVariable long id) {
        callsServices.deleteById(id);
    }

    // http:://localhost:8082/api/calls/get/{id}
    @GetMapping("get/{id}")
    public Calls getById(@PathVariable long id) {
        return callsServices.getById(id);
    }

    // http:://localhost:8082/api/calls/get
    @GetMapping("get")
    public List<Calls> getAllCalls() {
        return callsServices.getAll();
    }

    @PutMapping("assignToAgent/{callId}/{agentId}")
    public Calls assignedToAgent(@PathVariable long callId, @PathVariable long agentId) {
        return callsServices.assignedToAgent(callId, agentId);
    }
    @PutMapping("assignToAISystem/{callId}/{aiSystemId}")
    public Calls assignCallToAISystem(@PathVariable Long callId, @PathVariable Long aiSystemId) {
        return callsServices.assignCallToAISystem(callId, aiSystemId);
    }

    @PostMapping("addAndAssignToAgent/{agentId}")
    Calls assignedToAgent (@RequestBody Calls call, @PathVariable long agentId){
        return callsServices.assignedToAgent(call, agentId);
    }


    @PostMapping("addAndCheck")
    public boolean addAndCheck(@RequestBody Calls call) {
        Calls saved = callsServices.addCall(call);
        return callsServices.callRequiresHumanAgent(saved);
    }
    @PutMapping("autoAssign")
    public void autoAssignCallsToAgents(@RequestBody Set<Long> callIds) {
        callsServices.autoAssignCallsToAgents(callIds);
    }
    @PutMapping("assignCallsToAgents")
    public void assignCallsToAgents(@RequestBody Set<Long> callsIds) {
        callsServices.assignCallsToAgents(callsIds);
    }

@GetMapping("findByStatusAndAgentId/{status}/{agentId}")
List<Calls> findByStatusAndAssignedAgent_AgentsId(@PathVariable CallStatus status, @PathVariable long agentId) {
    return callsServices.findByStatusAndAssignedAgent_AgentsId(status, agentId);
}

@GetMapping("findByStatus/{status}")
List<Calls> findByStatus(@PathVariable CallStatus status) {
    return callsServices.findByStatus(status);
}

@GetMapping("findUnassigned")
List<Calls> findByAssignedAgentIsNull() {
    return callsServices.findByAssignedAgentIsNull();
}

@GetMapping("findByRequiredSkills/{skill}")
List<Calls> findByRequiredSkillsContains(@PathVariable CallSkills skill) {
    return callsServices.findByRequiredSkillsContains(skill);
}

@GetMapping("getTop5ByCallsDateTimeAndRequiredSkillsIn/{skill}")
List<Calls> findTop5ByRequiredSkillsContainsOrderByCallsDateTimeAsc(@PathVariable CallSkills skill) {
    return callsServices.findTop5ByRequiredSkillsContainsOrderByCallsDateTimeAsc(skill);
}

@GetMapping("existsByPhoneNumber/{phoneNumber}")
boolean existsByPhoneNumber(@PathVariable String phoneNumber) {
    return callsServices.existsByPhoneNumber(phoneNumber);
}

@GetMapping("countByStatus/{status}")
long countByStatus(@PathVariable CallStatus status) {
    return callsServices.countByStatus(status);
}
    @GetMapping("findCallsByAgent/{idAgent}")
    public List<Calls> findCallsByAgent(@PathVariable Long idAgent) {
        return callsServices.findCallsByAgent(idAgent);
    }

    @GetMapping("findCallsBySkill/{skill}")
    public List<Calls> findCallsBySkill(@PathVariable CallSkills skill) {
        return callsServices.findCallsBySkill(skill);
    }

    @GetMapping("countCallsByStatus")
    public List<Object[]> countCallsByStatus() {
        return callsServices.countCallsByStatus();
    }

    @GetMapping("findTopActiveAgents")
    public List<Object[]> findTopActiveAgents() {
        return callsServices.findTopActiveAgents();
    }

    @GetMapping("findTodayCalls")
    public List<Calls> findTodayCalls() {
        return callsServices.findTodayCalls();
    }
}

