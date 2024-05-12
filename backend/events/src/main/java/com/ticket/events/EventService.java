package com.ticket.events;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Service
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> getEvent(){
        return eventRepository.findAll();
    }

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public void createEvent(EventCreationRequest eventCreationRequest) {
        Event event = Event.builder()
                .eventName(eventCreationRequest.eventName())
                .date(eventCreationRequest.date())
                .location(eventCreationRequest.location())
                .NumberOfSeats(eventCreationRequest.numberOfSeats())
                .ownerID(eventCreationRequest.ownerID())

                .build();
        eventRepository.saveAndFlush(event);
        System.out.println("Creating a event");
    }

    public void updateEvent(Long eventId, EventCreationRequest eventCreationRequest) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));

        existingEvent.setEventName(eventCreationRequest.eventName());
        existingEvent.setDate(eventCreationRequest.date());
        existingEvent.setLocation(eventCreationRequest.location());
        existingEvent.setNumberOfSeats(eventCreationRequest.numberOfSeats());

        eventRepository.saveAndFlush(existingEvent);
        System.out.println("Updating an event with ID: " + eventId);
    }
    public void deleteEvent(Long eventId) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));

        eventRepository.delete(existingEvent);
        System.out.println("Deleting an event with ID: " + eventId);
    }


    public Event save(Event event) {
        return eventRepository.save(event);
    }
}
