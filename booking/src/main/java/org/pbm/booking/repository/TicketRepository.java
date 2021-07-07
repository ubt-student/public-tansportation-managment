package org.pbm.booking.repository;

import org.pbm.booking.domain.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Tickets, Long> {

    List<Tickets> getTicketsByUserEmail(String userEmail);
}
