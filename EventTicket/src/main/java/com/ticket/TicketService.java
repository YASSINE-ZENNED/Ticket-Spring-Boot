package com.ticket;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class TicketService {

    private final TicketRepository ticketRepository;


        public List<Ticket> getTicket(){

            return ticketRepository.findAll();
        }
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
    public void updateTicket(Long ticketId, TicketCreationRequest ticketCreationRequest) {
        Ticket existingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + ticketId));
//        existingTicket.setId(ticketCreationRequest.id());
        existingTicket.setTicketType(ticketCreationRequest.ticketType());
        existingTicket.setEventId(ticketCreationRequest.eventId());
        existingTicket.setTicketPrice(ticketCreationRequest.ticketPrice());
        existingTicket.setTicketTime(ticketCreationRequest.ticketTime());


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
