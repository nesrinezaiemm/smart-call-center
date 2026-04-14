package tn.esprit.zaiem_nesrine_arctic10.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.zaiem_nesrine_arctic10.entities.Project;

public interface IProjectRepository extends JpaRepository<Project,Long>{

}
