package project.transportation.publictransportationmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.transportation.publictransportationmanager.model.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
}
