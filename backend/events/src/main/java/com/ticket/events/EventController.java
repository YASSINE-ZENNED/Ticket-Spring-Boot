package com.ticket.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @PatchMapping("/UpdateSeat/{id}")
    public ResponseEntity<String> updateEventSeats(@PathVariable Long id, @RequestBody UpdatSeatsRequest request) {
        Optional<Event> eventOptional = eventService.findById(id);

        if (eventOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

        Event event = eventOptional.get(); // Safe to unwrap since we checked for presence


        int newNumberOfSeats =request.getNumberOfSeats();
        if (newNumberOfSeats < 0) {
            return ResponseEntity.badRequest().body("Number of seats cannot be negative."); // 400 Bad Request
        } else if ( newNumberOfSeats > event.getNumberOfSeats()) {
            return ResponseEntity.badRequest().body("Number of Seats not available"); // 400 Bad Request
        }

        event.setNumberOfSeats(event.getNumberOfSeats()-newNumberOfSeats); // Update seats using a setter
        event = eventService.save(event);

        return ResponseEntity.ok("Event hase been updated "); // 200 OK with updated event
    }
}
