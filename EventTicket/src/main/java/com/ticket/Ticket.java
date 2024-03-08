package com.ticket;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
@Entity
@Builder
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

//    private Long id;
    private Long UserId;
    private Long eventId;
    private String ticketType;
    private String ticketPrice;
    private String ticketTime;
    private String NumberOfSeats;


}
