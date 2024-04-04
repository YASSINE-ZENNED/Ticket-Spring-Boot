package com.ticket.events;

public record TicketRequest(String ticketType,
                            String ticketPrice,
                            String ticketTime,
                            String numberOfSeats,
                            Long eventId,
                            Long userId


) {
}
