package com.ticket.events;

import com.sun.jdi.request.EventRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/events")
public record EventController(EventService eventService) {

    @PostMapping
    public void registerEvent(@RequestBody EventRegestrationRequest eventRegestrationRequest){
        log.info("new customer registration {}", eventRegestrationRequest );
        eventService.registerEvent(eventRegestrationRequest);   }

}
