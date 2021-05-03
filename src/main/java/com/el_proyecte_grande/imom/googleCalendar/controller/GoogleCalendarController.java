package com.el_proyecte_grande.imom.googleCalendar.controller;
import com.el_proyecte_grande.imom.authentication.google_authentication.controller.GoogleAuthenticationController;
import com.el_proyecte_grande.imom.tasks_before_birth.model.TaskBeforeBirthDTO;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.security.GeneralSecurityException;
import java.util.*;


@Slf4j
@RestController
public class GoogleCalendarController {
    private static final String APPLICATION_NAME = "iMom";
    private static Calendar client;


    @CrossOrigin
    @ApiOperation("Operation to return users calendar")
    @GetMapping("/calendar")
    public CalendarListEntry getCalendar() throws IOException, GeneralSecurityException {
        if (client == null){
            buildCalendar();
        }
        return client.calendarList().get("primary").execute();
    }

    @CrossOrigin
    @ApiOperation("Operation to return all events")
    @GetMapping("/calendar-events")
    public Events getCalendarEvents() throws IOException, GeneralSecurityException {
        if (client == null){
            buildCalendar();
        }

        Events events = client.events().list("primary").execute();

        List<Event> items = events.getItems();
        for (Event event : items) {
            System.out.println(event.getSummary()); }

        return events;
    }


    @CrossOrigin
    @ApiOperation("Operation to create new event")
    @PostMapping("/new-event")
    public Event createNewEvent(@RequestParam TaskBeforeBirthDTO task, @RequestParam String eventDateTime) throws IOException, GeneralSecurityException {
        if (client == null){
            buildCalendar();
        }

        DateTime startDateTime = new DateTime(eventDateTime);
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime);

        EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail(GoogleAuthenticationController.userDetails.getEmail()),
        };

        Event newEvent = new Event()
                .setSummary(task.getTaskName())
                .setDescription(task.getTaskText())
                .setStatus(String.valueOf(task.getTaskStatus()))
                .setStart(start)
                .setAttendees(Arrays.asList(attendees));

        client.events().insert("primary", newEvent);

        return newEvent;
    }


    private void buildCalendar() throws IOException, GeneralSecurityException {
        if (GoogleAuthenticationController.authorization == null){
            GoogleAuthenticationController.getCredentials();
        }
        try {
            client = new Calendar.Builder(GoogleAuthenticationController.httpTransport,
                    GoogleAuthenticationController.JSON_FACTORY, GoogleAuthenticationController.authorization)
                    .setApplicationName(APPLICATION_NAME).build();
        } catch (Exception e) {
            log.warn("Exception while handling OAuth2 callback (" + e.getMessage() + ")."
                    + " Redirecting to google connection status page.");
        }
    }
}



