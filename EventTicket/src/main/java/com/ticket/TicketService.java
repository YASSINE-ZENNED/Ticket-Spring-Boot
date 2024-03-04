package com.ticket;

import org.springframework.stereotype.Service;

@Service
public class TicketService {

        public void createTicket(TicketCreationRequest ticketCreationRequest) {
            Ticket ticket = Ticket.builder()
                    .ticketType(ticketCreationRequest.ticketType())
                    .ticketPrice(ticketCreationRequest.ticketPrice())
                    .ticketTime(ticketCreationRequest.ticketTime())
                    .NumberOfSeats(ticketCreationRequest.numberOfSeats())
                    .eventId(ticketCreationRequest.eventId())
                    .UserId(ticketCreationRequest.userId())
                    .build();

            System.out.println("Creating a ticket");
        }

}
