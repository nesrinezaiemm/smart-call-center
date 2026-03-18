package tn.esprit.zaiem_nesrine_arctic10.services;


import tn.esprit.zaiem_nesrine_arctic10.entities.AISystem;
import tn.esprit.zaiem_nesrine_arctic10.entities.Calls;

import java.util.List;
public interface IIASystemService {
    AISystem addAISystem(AISystem system);
    AISystem updateAISystem(AISystem system);
    void deleteById(long systemId);
    void deleteAISystem(AISystem system);
    AISystem getById(long systemId);
    List<AISystem> getAll();
}