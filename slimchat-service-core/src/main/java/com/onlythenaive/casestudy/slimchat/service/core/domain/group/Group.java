package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.time.Instant;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.utility.gravatar.Gravatars;

/**
 * Chat group.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    private String id;
    private String caption;
    private Collection<Profile> participants;
    private Collection<Profile> moderators;
    private Boolean principalIsParticipant;
    private Boolean principalIsModerator;
    private Instant lastModifiedAt;
    private Instant createdAt;

    /**
     * Gets a Gravatar URL for this group.
     *
     * This method is just for presentation means of plain frontend view templates.
     * Same results should be achieved by using of custom helpers registered into the template engine.
     *
     * @return a Gravatar URL.
     */
    @JsonIgnore
    public String getGravatarUrl() {
        return Gravatars.url(this.id, 64);
    }
}
