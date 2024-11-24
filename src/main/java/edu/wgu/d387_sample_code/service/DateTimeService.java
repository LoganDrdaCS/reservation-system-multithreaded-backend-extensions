package edu.wgu.d387_sample_code.service;

import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service  // Marks this class as a service, so Spring manages it as a bean
public class DateTimeService {

    // Method to convert times to different time zones and return formatted strings
    public String[] convertTimes() {
        String[] meetingInvitationArray = {"Placeholder"};  // Array to hold the formatted time strings

        // Define the time zones for Eastern, Mountain, UTC, and Pacific Time
        ZoneId zEastern = ZoneId.of("America/New_York");
        ZoneId zMountain = ZoneId.of("America/Denver");
        ZoneId zUTC = ZoneId.of("UTC");
        ZoneId zPacific = ZoneId.of("America/Los_Angeles");

        // Get the current time in Eastern Time zone
        ZonedDateTime easternTime = ZonedDateTime.now(zEastern);

        // Convert the Eastern time to other time zones
        ZonedDateTime mountainTime = easternTime.withZoneSameInstant(zMountain);
        ZonedDateTime utcTime = easternTime.withZoneSameInstant(zUTC);

        // Formatter for 12-hour time with AM/PM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");

        // Format the times for each time zone
        String easternTimeStr = easternTime.format(formatter);
        String mountainTimeStr = mountainTime.format(formatter);
        String utcTimeStr = utcTime.format(formatter);

        // Combine the formatted times into a message string
        meetingInvitationArray[0] = String.format(
                "Join us on September 12, 2024 for an online, live presentation held at the Landon Hotel at %s Eastern Time | %s Mountain Time | %s UTC",
                easternTimeStr, mountainTimeStr, utcTimeStr
        );

        return meetingInvitationArray;  // Return the formatted meeting invitation
    }
}
