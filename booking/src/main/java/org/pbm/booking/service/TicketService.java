package org.pbm.booking.service;

import lombok.RequiredArgsConstructor;
import org.pbm.booking.domain.model.Tickets;
import org.pbm.booking.repository.TicketRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    @Transactional
    public List<Tickets> getTicketsByEmail(String email) {
        return ticketRepository.getTicketsByUserEmail(email);
    }

    @Transactional
    public void registerNewTicket(Tickets tickets){
        ticketRepository.save(tickets);
    }

    @Transactional
    public void deleteTicket(Tickets tickets) {
        ticketRepository.delete(tickets);
    }
}
