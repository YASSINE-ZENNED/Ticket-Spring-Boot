package com.ticket;

public record TicketCreationRequest(String ticketType,
                                    String ticketPrice,
                                    String ticketTime,
                                    String numberOfSeats,
                                    Long eventId,
                                    Long userId


) {
}
