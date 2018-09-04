package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.utility.datetime.PrettyTimestamps;

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
    private Group group;
    private Profile recipient;
    private String chatId;
    private Boolean own;
    private Instant createdAt;

    /**
     * Gets a pretty representation of message creation timestamp.
     *
     * This method is just for presentation means of plain frontend view templates.
     * Same results should be achieved by using of custom helpers registered into the template engine.
     *
     * @return a message creation timestamp.
     */
    @JsonIgnore
    public String getPrettyCreatedAt() {
        return PrettyTimestamps.pretty(this.createdAt);
    }
}
