package com.ticket;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
        private final TicketService ticketService;
    @GetMapping
    public Ticket getTicket() {

      Ticket  ticket = new Ticket();
         log.info("Ticket : {}", ticket);
        log.info("Creating a ticket");
        return ticket;

    }

    @PostMapping
    public void CreateTicket(@RequestBody TicketCreationRequest ticket) {
        log.info("Creating a ticket");
        ticketService.createTicket(ticket);
    }



}
