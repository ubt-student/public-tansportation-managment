package project.transportation.publictransportationmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.transportation.publictransportationmanager.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
