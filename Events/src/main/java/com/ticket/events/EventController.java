package com.ticket.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;


    @GetMapping
    public Event getEvent() {

        Event  event = new Event();

        log.info("Event : {}", event);
        log.info("Creating an Event");
        return event;

    }

    @PostMapping
    public void CreateEvent(@RequestBody EventCreationRequest event) {
        log.info("Creating an event");
        eventService.createEvent(event);
    }


}
