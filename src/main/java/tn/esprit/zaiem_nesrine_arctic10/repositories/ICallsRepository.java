package tn.esprit.zaiem_nesrine_arctic10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.zaiem_nesrine_arctic10.entities.CallSkills;
import tn.esprit.zaiem_nesrine_arctic10.entities.CallStatus;
import tn.esprit.zaiem_nesrine_arctic10.entities.Calls;

import java.util.List;

@Repository
public interface ICallsRepository extends JpaRepository<Calls,Long> {

    List<Calls> findByStatus(CallStatus status);
    List<Calls> findByStatusAndAssignedAgent_AgentsId(CallStatus status, long agentId);
    List<Calls> findByAssignedAgentIsNull();
    List<Calls> findByRequiredSkillsContains(CallSkills skill);
    List<Calls> findTop5ByRequiredSkillsContainsOrderByCallsDateTimeAsc(CallSkills skill);
    boolean existsByPhoneNumber(String phoneNumber);
    long countByStatus(CallStatus status);
    @Query("SELECT c FROM Calls c WHERE c.assignedAgent.agentsId = :idAgent")
    List<Calls> findCallsByAgent(@Param("idAgent") Long idAgent);
    @Query("SELECT c FROM Calls c WHERE :skill MEMBER OF c.requiredSkills")
    List<Calls> findCallsBySkill(@Param("skill") CallSkills skill);
    @Query("SELECT c.status, COUNT(c) FROM Calls c GROUP BY c.status")
    List<Object[]> countCallsByStatus();
    @Query("SELECT c.assignedAgent.name, COUNT(c) FROM Calls c GROUP BY c.assignedAgent HAVING COUNT(c) > 5")
    List<Object[]> findTopActiveAgents();

    @Query("SELECT c FROM Calls c WHERE FUNCTION('DATE', c.callsDateTime) = CURRENT_DATE")
    List<Calls> findTodayCalls();
}
