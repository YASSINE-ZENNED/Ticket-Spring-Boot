package com.ticket.events;

import org.springframework.stereotype.Service;

@Service

public record EventService(EventRepository eventRepository) {
    public void registerEvent(EventRegestrationRequest request) {
        Event event=Event.builder()
                .eventName(request.eventName())
                .date(request.date())
                .location(request.location())
                .build();
        eventRepository.save(event);

    }
}
