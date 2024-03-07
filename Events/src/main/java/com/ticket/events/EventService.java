package com.ticket.events;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@AllArgsConstructor

@Service
public class EventService {

    private final EventRepository eventRepository;

    public void createEvent(EventCreationRequest eventCreationRequest) {
        Event event = Event.builder()
                .eventName(eventCreationRequest.eventName())
                .date(eventCreationRequest.date())
                .location(eventCreationRequest.location())

                .build();
        eventRepository.saveAndFlush(event);
        System.out.println("Creating a event");
    }

}
