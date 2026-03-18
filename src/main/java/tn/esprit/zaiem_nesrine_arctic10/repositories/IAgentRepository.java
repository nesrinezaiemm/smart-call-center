package tn.esprit.zaiem_nesrine_arctic10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.zaiem_nesrine_arctic10.entities.Agent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.zaiem_nesrine_arctic10.entities.CallSkills;
import java.util.List;
import java.util.Set;
public interface IAgentRepository extends JpaRepository<Agent,Long> {
    List<Agent> findByAvailableTrue();
}

