package com.ticket.eventticket;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class TicketService {

    private final TicketRepository ticketRepository;


        public List<Ticket> getTicket(){

            return ticketRepository.findAll();
        }
        public void createTicket(TicketRequest ticketRequest) {
            Ticket ticket = Ticket.builder()
                    .ticketType(ticketRequest.ticketType())
                    .ticketPrice(ticketRequest.ticketPrice())
                    .ticketTime(ticketRequest.ticketTime())
                    .NumberOfSeats(ticketRequest.numberOfSeats())
                    .eventId(ticketRequest.eventId())
                    .UserId(ticketRequest.userId())
                    .build();
            ticketRepository.saveAndFlush(ticket);
            System.out.println("Creating a ticket");
        }
    public void updateTicket(Long ticketId, TicketRequest ticketRequest) {
        Ticket existingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + ticketId));
//        existingTicket.setId(ticketCreationRequest.id());
        existingTicket.setTicketType(ticketRequest.ticketType());
        existingTicket.setEventId(ticketRequest.eventId());
        existingTicket.setTicketPrice(ticketRequest.ticketPrice());
        existingTicket.setTicketTime(ticketRequest.ticketTime());


        ticketRepository.saveAndFlush(existingTicket);
        System.out.println("Updating a ticket with ID: " + ticketId);
    }
    public void deleteTicket(Long ticketId) {
        Ticket existingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + ticketId));

        ticketRepository.delete(existingTicket);
        System.out.println("Deleting a ticket with ID: " + ticketId);
    }

}
