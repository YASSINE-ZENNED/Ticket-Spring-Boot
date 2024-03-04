package com.ticket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @GetMapping
    public Ticket createTicket() {

      Ticket  ticket = new Ticket();

         log.info("Ticket : {}", ticket);
        log.info("Creating a ticket");
        return ticket;

    }
    @PostMapping
    public Ticket updateTicket(@RequestBody Ticket ticket) {
        log.info("Updating a ticket");
        return ticket;
    }
    @PostMapping
    public TicketCreationRequest CreateTicket(@RequestBody TicketCreationRequest ticket) {
        log.info("Creating a ticket");
        return ticket;
    }



}
