package com.ticket.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;


    @GetMapping("/AllEvents")
    public List<Event> getEvent() {

        Event  event = new Event();

        log.info("Creating an Event krakend");
        return eventService.getEvent();



    }

    @PostMapping("/CreateEvent")
    public void CreateEvent(@RequestBody EventCreationRequest event) {
        log.info("Creating an event");
        eventService.createEvent(event);
    }
    @PutMapping("/UpdateEvent/{eventId}")
    public void updateEvent(@PathVariable Long eventId, @RequestBody EventCreationRequest event) {
        log.info("Updating an event with ID: {}", eventId);
        eventService.updateEvent(eventId, event);
    }
    @DeleteMapping("/DeleteEvent/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        log.info("Deleting an event with ID: {}", eventId);
        eventService.deleteEvent(eventId);
    }


}
