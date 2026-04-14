package tn.esprit.zaiem_nesrine_arctic10.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.zaiem_nesrine_arctic10.entities.Agent;
import tn.esprit.zaiem_nesrine_arctic10.entities.CallSkills;
import tn.esprit.zaiem_nesrine_arctic10.services.IAgentService;

import java.util.List;
import java.util.Set;

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
    @GetMapping("available")
    public List<Agent> getAvailableAgents() {
        return agentService.getAvailableAgents();
    }
    @GetMapping("bySkill/{skill}")
    public List<Agent> getAgentsBySkill(@PathVariable CallSkills skill) {
        return agentService.getAgentsBySkill(skill);
    }
    @GetMapping("availableWithSkills")
    public List<Agent> getAvailableAgentsWithSkills(@RequestParam Set<CallSkills> skills) {
        return agentService.getAvailableAgentsWithSkills(skills);
    }
    @GetMapping("findBySkillJPQL/{skill}")
    public List<Agent> findAgentsBySkill(@PathVariable CallSkills skill) {
        return agentService.findAgentsBySkill(skill);
    }

    @GetMapping("mostCompetent/{callsId}")
    public List<Agent> findMostCompetentAgentForCall(@PathVariable Long callsId) {
        return agentService.findMostCompetentAgentForCall(callsId);
    }
   // @GetMapping("byClient/{client}")
    //public List<Agent> findAgentsByClient(@PathVariable String client) {
      //  return agentService.findAgentsByClient(client);
    //}
    //@GetMapping("sumBudget/{name}")
    //public Double getSumBudgetByAgentName(@PathVariable String name) {
      //  return agentService.getSumBudgetByAgentName(name);
    //}
}