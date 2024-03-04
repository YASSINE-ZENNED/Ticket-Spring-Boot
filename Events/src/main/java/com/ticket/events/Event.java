package com.ticket.events;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Entity;


import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor

@NoArgsConstructor
@Entity

public class Event {


    private String eventName;
    private LocalDate date;
    private String location;
    @Id
    @GeneratedValue
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
