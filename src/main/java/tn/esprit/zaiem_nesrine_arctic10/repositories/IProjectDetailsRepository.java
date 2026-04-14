package tn.esprit.zaiem_nesrine_arctic10.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.zaiem_nesrine_arctic10.entities.ProjectDetails;

public interface IProjectDetailsRepository extends JpaRepository<ProjectDetails,Long>{
    @Query("""
            SELECT SUM(pd.budget)
            FROM ProjectDetails pd
            WHERE pd = (
                SELECT p.projectDetails
                FROM Agent a
                JOIN a.projects p
                WHERE a.name = :agentName
            )
            """)
    double sumOfBudgetByAgentName(String agentName);
}
