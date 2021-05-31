package com.el_proyecte_grande.imom.googleCalendar.controller;
import com.el_proyecte_grande.imom.authentication.google_authentication.controller.GoogleAuthenticationController;
import com.el_proyecte_grande.imom.googleCalendar.model.UserEvent;
import com.el_proyecte_grande.imom.tasks_before_birth.model.TaskBeforeBirthDTO;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.security.GeneralSecurityException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public List<UserEvent> getCalendarEvents() throws IOException, GeneralSecurityException {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<UserEvent> userEventsList = new ArrayList();
        if (client == null){
            buildCalendar();
        }
        Events events = client.events().list("primary").execute();

        List<Event> items = events.getItems();
        for (Event event : items) {
            UserEvent userEvent = new UserEvent();
            Map<String, String> data = new HashMap<>();
                if ((event.getStart().getDateTime() != null)) {
                    String dateTime = event.getStart().getDateTime().toStringRfc3339();
                    String[] parts = dateTime.split("\\+");
                    String part1 = parts[0];
//                    DateTime dateTime1 = new DateTime(part1);
                    userEvent.setStartDateTime(part1);
                }
                if ((event.getEnd().getDateTime() != null)) {
                    String dateTime = event.getEnd().getDateTime().toStringRfc3339();
                    String[] parts = dateTime.split("\\+");
                    String part1 = parts[0];
                    userEvent.setEndDateTime(part1);
                    userEvent.setName(event.getSummary());
                    userEvent.setDescription(event.getDescription());
                    userEvent.setLocation(event.getLocation());

                }
//                data.put("name", event.getSummary());
//                data.put("description", event.getDescription());
//                data.put("location", event.getLocation());
//                data.put("color", "#3365ed");
//                userEvent.setData(data);
            System.out.println(event.getSummary());

            userEventsList.add(userEvent);
            }

        return userEventsList;
        }


//    @RequestParam String eventDateTime
    @CrossOrigin
    @ApiOperation("Operation to create new event")
    @PostMapping("/new-event")
    public Event createNewEvent(@RequestBody UserEvent userEvent) throws IOException, GeneralSecurityException {
        System.out.println("I'M HERE");

        if (client == null){
            buildCalendar();
        }

        String[] partsStart = userEvent.getStartDateTime().split("Z");
        String startTime = partsStart[0];
        startTime += ":00.000";
        String[] partsEnd = userEvent.getEndDateTime().split("Z");
        String endTime = partsEnd[0];
        endTime += ":00.000";
        System.out.println(startTime);
        System.out.println(endTime);
        DateTime startDateTime = new DateTime(startTime);
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime);

        System.out.println(start);

        DateTime endDateTime = new DateTime(endTime);
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime);

        System.out.println(end);

//        EventAttendee[] attendees = new EventAttendee[] {
//                new EventAttendee().setEmail("lashark11@gmail.com"),
//        };

        Event newEvent = new Event()
                .setSummary(userEvent.getName())
                .setDescription(userEvent.getDescription())
//                .setStatus(String.valueOf(userEvent.getStatus()))
                .setLocation(userEvent.getLocation())
                .setStart(start)
                .setEnd(end);
//                .setAttendees(Arrays.asList(attendees));

        String calendarId = "primary";
        newEvent = client.events().insert(calendarId, newEvent).execute();
        System.out.printf("Event created: %s\n", newEvent.getHtmlLink());
//        client.events().insert("primary", newEvent);
        System.out.println("END");
        GoogleAuthenticationController.getCredentials();

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


