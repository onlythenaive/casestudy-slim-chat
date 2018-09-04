package com.onlythenaive.casestudy.slimchat.service.core.utility.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.lang.Nullable;

/**
 * Utility helper for projection of timestamps into human-readable form.
 *
 * @author Ilia Gubarev
 */
public final class PrettyTimestamps {

    private static final DateTimeFormatter PRETTY_DATE = DateTimeFormatter.ofPattern("dd MMMMM yyyy");
    private static final DateTimeFormatter PRETTY_TIME = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Projects specified timestamps into its human-readable form.
     *
     * @param timestamp a timestamp to be projected.
     * @return a human-readable projections of the timestamp.
     */
    @Nullable
    public static String pretty(@Nullable Instant timestamp) {
        if (timestamp == null) {
            return null;
        }
        LocalDateTime datetime = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        LocalDate date = datetime.toLocalDate();
        LocalTime time = datetime.toLocalTime();
        return date.isBefore(today()) ? prettyDateTime(date, time) : prettyTime(time);
    }

    private static String prettyDateTime(LocalDate date, LocalTime time) {
        String prettyDate = date.format(PRETTY_DATE);
        String prettyTime = prettyTime(time);
        return String.format("%s %s", prettyTime, prettyDate);
    }

    private static String prettyTime(LocalTime time) {
        return time.format(PRETTY_TIME);
    }

    private static LocalDate today() {
        return LocalDate.now();
    }

    private PrettyTimestamps() {

    }
}
