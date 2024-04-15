package com.ticket.eventticket;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
        private final TicketService ticketService;

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate; // Adjust as needed

    @GetMapping
    public List<Ticket> getTicket() {

      Ticket  ticket = new Ticket();

        return ticketService.getTicket();

    }

    @PostMapping
    public void CreateTicket(@RequestBody TicketRequest ticket) {
        log.info("Creating a ticket");

        Message<String> message = MessageBuilder
                .withPayload((ticket.numberOfSeats()+" "+ticket.eventId()))
                .setHeader(KafkaHeaders.TOPIC, "ticket")
                .build();

        ticketService.createTicket(ticket);

        kafkaTemplate.send(message);

        // Use your actual topic name
        log.info(" Sent ticket to Kafka: {}", message);

    }
    @PutMapping("/{ticketId}")
    public void updateTicket(@PathVariable Long ticketId, @RequestBody TicketRequest ticket) {
        log.info("Updating an event with ID: {}", ticketId);
        ticketService.updateTicket(ticketId, ticket);
    }
    @DeleteMapping("/{ticketId}")
    public void deleteTicket(@PathVariable Long ticketId) {
        log.info("Deleting a ticket with ID: {}", ticketId);
        ticketService.deleteTicket(ticketId);
    }



}
