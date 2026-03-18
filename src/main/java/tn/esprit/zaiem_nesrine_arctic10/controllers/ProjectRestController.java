package tn.esprit.zaiem_nesrine_arctic10.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.zaiem_nesrine_arctic10.entities.Project;
import tn.esprit.zaiem_nesrine_arctic10.entities.Agent;
import tn.esprit.zaiem_nesrine_arctic10.services.IProjectServices;

import java.util.List;

@RequestMapping("project")
@RestController
@RequiredArgsConstructor
public class ProjectRestController {

    private final IProjectServices projectServices;

    @PostMapping("add")
    public Project addProject(@RequestBody Project project){
        return projectServices.addProject(project);
    }
    @PutMapping("update")
    public Project updateProject(@RequestBody Project project){
        return projectServices.updateProject(project);
    }
    @GetMapping("get")
    public List<Project> getAllProjects(){
        return projectServices.getAll();   // ✔ correct method
    }
    @GetMapping("get/{id}")
    public Project getProjectById(@PathVariable long id){
        return projectServices.getProjectById(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteProject(@PathVariable long id){
        projectServices.deleteProjectById(id);   // ✔ correct method
    }

    @GetMapping("{id}/agents")
    public List<Agent> getAgents(@PathVariable long id){
        return projectServices.getAgents(id);
    }
    //@GetMapping("assignToAgents/{projectId}/{agentsId}")
    @PutMapping("assignToAgent/{projectId}/{agentsId}")

    public Project assignToAgents(@PathVariable Long projectId, @PathVariable Long agentsId) {
        return projectServices.assignToAgents(projectId , agentsId);
    }
}