package com.ticket.events;

import java.time.LocalDate;

public record EventRegestrationRequest(Integer id,
                                       String eventName,
                                       LocalDate date,
                                       String location
                                       ) {

}
