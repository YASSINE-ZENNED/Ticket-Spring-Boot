package com.ticket.events;

import java.time.LocalDate;

public record EventCreationRequest(Integer id,
                                   String eventName,
                                   LocalDate date,
                                   String location,
                                   int numberOfSeats
                                       ) {

}
