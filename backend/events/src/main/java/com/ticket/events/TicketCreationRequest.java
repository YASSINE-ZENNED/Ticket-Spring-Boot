package com.ticket.events;



public record TicketCreationRequest(String ticketType,
                                    String ticketPrice,
                                    String ticketTime,
                                    String numberOfSeats,
                                    Long eventId,
                                    Long userId


) {


}
