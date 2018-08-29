package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

/**
 * Chat message.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String id;
    private String text;
    private Profile author;
    private Boolean writtenByPrincipal;
    private Profile recipient;
    private Group group;
    private Instant createdAt;

    public boolean isAffiliatedToGroup() {
        return this.group != null;
    }

    public String getPrettyTimestamp() {
        LocalDateTime datetime = LocalDateTime.ofInstant(createdAt, ZoneId.systemDefault());
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
}
