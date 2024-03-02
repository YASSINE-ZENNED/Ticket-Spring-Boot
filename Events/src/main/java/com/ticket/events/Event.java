package com.ticket.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor

@NoArgsConstructor
@Entity

public class Event {

    @javax.persistence.Id
    @Id
    @SequenceGenerator(
            name="event-id-sequence",
            sequenceName="event-id-sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event-id-sequence"
    )

    private Integer id;
    private String eventName;
    private LocalDate date;
    private String location;

}
