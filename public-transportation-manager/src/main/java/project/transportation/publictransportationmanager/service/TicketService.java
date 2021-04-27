package project.transportation.publictransportationmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.transportation.publictransportationmanager.model.Ticket;
import project.transportation.publictransportationmanager.repo.TicketRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketrepository;

    public String add(List<Ticket> acc) {
        System.out.println("in Ticket add");
        for (Ticket a : acc) {
            ticketrepository.save(a);
        }
        return "save completed";
    }

    public List<Ticket> getAll() {
        System.out.println("Ticket get all");
        return ticketrepository.findAll();
    }

    public Optional<Ticket> get(Long id) {
        System.out.println("Ticket get");
        return ticketrepository.findById(id);
    }

    public String delete(Long id) {
        System.out.println("Ticket delete");
        ticketrepository.deleteById(id);
        return "Successful deletion";
    }

    public String delete(List<Ticket> acc) {
        System.out.println("Ticket delete all");
        ticketrepository.deleteAll(acc);
        return "Multiple deletion successful";
    }
}