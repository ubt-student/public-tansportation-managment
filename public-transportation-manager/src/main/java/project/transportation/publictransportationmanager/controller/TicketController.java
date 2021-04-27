package project.transportation.publictransportationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.transportation.publictransportationmanager.model.Ticket;
import project.transportation.publictransportationmanager.service.TicketService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    @ResponseBody
    public String newAcc(@RequestBody List<Ticket> acc) {
        return ticketService.add(acc);

    }

    @GetMapping(value = "/{id}")
    public Optional<Ticket> getId(@PathVariable(value = "id") Long id) {
        return ticketService.get(id);
    }

    @GetMapping()
    public List<Ticket> getAll() {
        return ticketService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public String deleteById(@PathVariable(value = "id") Long id) {
        return ticketService.delete(id);
    }

    @DeleteMapping()
    @ResponseBody
    public String deleteByBody(@RequestBody List<Ticket> acc) {
        return ticketService.delete(acc);
    }
}
