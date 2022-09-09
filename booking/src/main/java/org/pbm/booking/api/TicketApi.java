package org.pbm.booking.api;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.pbm.booking.domain.model.Tickets;
import org.pbm.booking.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "booking/ticket")
@RequiredArgsConstructor
public class TicketApi {

    private final TicketService ticketService;

    @GetMapping
    public List<Tickets> getTicketsByUserEmail(@RequestParam String email){
        return ticketService.getTicketsByEmail(email);
    }

    @PostMapping
    public ResponseEntity<String> registerNewTicket(@RequestBody Tickets tickets){
        ticketService.registerNewTicket(tickets);
        return ResponseEntity.ok("{}");
    }

    @PostMapping("delete")
    public ResponseEntity<String> deleteTicket(@RequestBody Tickets tickets){
        ticketService.deleteTicket(tickets);
        return ResponseEntity.ok("{}");
    }

}
