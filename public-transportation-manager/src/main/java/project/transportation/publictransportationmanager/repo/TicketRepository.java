package project.transportation.publictransportationmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.transportation.publictransportationmanager.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
