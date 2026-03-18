package tn.esprit.zaiem_nesrine_arctic10.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.zaiem_nesrine_arctic10.entities.Agent;
import tn.esprit.zaiem_nesrine_arctic10.services.IAgentService;

import java.util.List;

@RestController
@RequestMapping("agent")
@RequiredArgsConstructor
public class AgentRestController {

    private final IAgentService agentService;
    @PostMapping("add")
    public Agent addAgent(@RequestBody Agent agent) {
        return agentService.addAgent(agent);
    }
    @PutMapping("update")
    public Agent updateAgent(@RequestBody Agent agent) {
        return agentService.updateAgent(agent);
    }
    @GetMapping("get")
    public List<Agent> getAllAgents() {
        return agentService.getAll();
    }
    @GetMapping("get/{id}")
    public Agent getAgentById(@PathVariable long id) {
        return agentService.getById(id);
    }
    @DeleteMapping("delete/{id}")
    public void deleteAgent(@PathVariable long id) {
        agentService.deleteById(id);
    }
}