package com.ticket.events;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

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

public class Event {


    private String eventName;
    private LocalDate date;
    private String location;
    @Id
    @GeneratedValue
    private Long id;


}
