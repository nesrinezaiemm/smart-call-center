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
    List<Agent> findBySkillsContaining(CallSkills skill);
    List<Agent> findByAvailableTrueAndSkillsIn(Set<CallSkills> skills);
    @Query("SELECT a FROM Agent a WHERE :skill MEMBER OF a.skills")
    List<Agent> findAgentsBySkill(@Param("skill") CallSkills skill);
    @Query("SELECT a FROM Agent a JOIN a.skills s, Calls c WHERE c.callsId = :callsId AND s MEMBER OF c.requiredSkills AND a.available = true GROUP BY a ORDER BY COUNT(s) DESC")
    List<Agent> findMostCompetentAgentForCall(@Param("callsId") Long callsId);

}

