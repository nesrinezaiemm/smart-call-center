package tn.esprit.zaiem_nesrine_arctic10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.zaiem_nesrine_arctic10.entities.Calls;
@Repository
public interface ICallsRepository extends JpaRepository<Calls,Long> {

}
