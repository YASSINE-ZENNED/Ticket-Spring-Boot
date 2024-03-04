package com.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class Ticket {

    private Long id;
    private Long UserId;
    private Long eventId;
    private String ticketType;
    private String ticketPrice;
    private String ticketTime;
    private String NumberOfSeats;


    public Ticket() {

    }
}
