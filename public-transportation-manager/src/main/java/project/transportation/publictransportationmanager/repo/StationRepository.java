package project.transportation.publictransportationmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.transportation.publictransportationmanager.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
