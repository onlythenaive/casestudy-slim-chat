package com.onlythenaive.casestudy.slimchat.service.core.utility.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Options;
import com.onlythenaive.casestudy.slimchat.service.core.configuration.web.GenericTemplateHelperBean;

/**
 * Template helper for rendering timestamps.
 *
 * @author Ilia Gubarev
 */
@Component
public class PrettyTimestampTemplateHelperBean extends GenericTemplateHelperBean<Instant> {

    @Override
    public String getName() {
        return "prettyTimestamp";
    }

    @Override
    public Object apply(Instant timestamp, Options options) {
        if (timestamp == null) {
            return null;
        }
        LocalDateTime datetime = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        LocalDate date = datetime.toLocalDate();
        LocalTime time = datetime.toLocalTime();
        String timeString = time.format(DateTimeFormatter.ofPattern("HH:mm"));
        if (date.isBefore(LocalDate.now())) {
            String dateString = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
            return timeString + " " + dateString;
        } else {
            return timeString;
        }
    }
}
