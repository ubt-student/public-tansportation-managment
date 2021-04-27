package project.transportation.publictransportationmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.transportation.publictransportationmanager.model.Cancellation;

@Repository
public interface CancellationRepository extends JpaRepository<Cancellation, Long> {
}
