package com.ticket;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
        private final TicketService ticketService;
    @GetMapping
    public List<Ticket> getTicket() {

      Ticket  ticket = new Ticket();

         log.info("Ticket : {}", ticket);
        log.info("Creating a ticket");
        return ticketService.getTicket();

    }

    @PostMapping
    public void CreateTicket(@RequestBody TicketCreationRequest ticket) {
        log.info("Creating a ticket");
        ticketService.createTicket(ticket);
    }
    @PutMapping("/{ticketId}")
    public void updateTicket(@PathVariable Long ticketId, @RequestBody TicketCreationRequest ticket) {
        log.info("Updating an event with ID: {}", ticketId);
        ticketService.updateTicket(ticketId, ticket);
    }
    @DeleteMapping("/{ticketId}")
    public void deleteTicket(@PathVariable Long ticketId) {
        log.info("Deleting a ticket with ID: {}", ticketId);
        ticketService.deleteTicket(ticketId);
    }



}
