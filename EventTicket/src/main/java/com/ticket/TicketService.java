package com.ticket;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@AllArgsConstructor

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

        public void createTicket(TicketCreationRequest ticketCreationRequest) {
            Ticket ticket = Ticket.builder()
                    .ticketType(ticketCreationRequest.ticketType())
                    .ticketPrice(ticketCreationRequest.ticketPrice())
                    .ticketTime(ticketCreationRequest.ticketTime())
                    .NumberOfSeats(ticketCreationRequest.numberOfSeats())
                    .eventId(ticketCreationRequest.eventId())
                    .UserId(ticketCreationRequest.userId())
                    .build();
            ticketRepository.saveAndFlush(ticket);
            System.out.println("Creating a ticket");
        }

}
