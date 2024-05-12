package com.ticket.eventticket;

public record TicketRequest(String ticketType,
                            String ticketPrice,
                            String ticketTime,
                            String numberOfSeats,
                            Long eventId,
                            String userId


) {
}
