package project.transportation.publictransportationmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.transportation.publictransportationmanager.model.Booking;
import project.transportation.publictransportationmanager.model.Bus;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByBus(Bus bus);

}
