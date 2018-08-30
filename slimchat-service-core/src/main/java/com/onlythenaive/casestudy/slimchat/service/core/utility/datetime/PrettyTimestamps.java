package com.onlythenaive.casestudy.slimchat.service.core.utility.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class PrettyTimestamps {

    public static String pretty(Instant timestamp) {
        if (timestamp == null) {
            return null;
        }
        LocalDateTime datetime = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        LocalDate date = datetime.toLocalDate();
        LocalTime time = datetime.toLocalTime();
        String timeString = time.format(DateTimeFormatter.ofPattern("hh:mm"));
        if (date.isBefore(LocalDate.now())) {
            String dateString = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
            return timeString + " " + dateString;
        } else {
            return timeString;
        }
    }

    private PrettyTimestamps() {

    }
}
