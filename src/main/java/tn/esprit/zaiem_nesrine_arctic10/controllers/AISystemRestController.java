package tn.esprit.zaiem_nesrine_arctic10.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.zaiem_nesrine_arctic10.entities.AISystem;
import tn.esprit.zaiem_nesrine_arctic10.services.IIASystemService;

import java.util.List;

@RestController
@RequestMapping("aisystem")
@RequiredArgsConstructor
public class AISystemRestController {

    private final IIASystemService iaSystemService;
    @PostMapping("add")
    public AISystem addAISystem(@RequestBody AISystem system) {
        return iaSystemService.addAISystem(system);
    }
    @PutMapping("update")
    public AISystem updateAISystem(@RequestBody AISystem system) {
        return iaSystemService.updateAISystem(system);
    }

    @GetMapping("get")
    public List<AISystem> getAllAISystems() {
        return iaSystemService.getAll();
    }


    @GetMapping("get/{id}")
    public AISystem getAISystemById(@PathVariable long id) {
        return iaSystemService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteAISystem(@PathVariable long id) {
        iaSystemService.deleteById(id);
    }
}