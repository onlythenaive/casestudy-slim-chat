package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlythenaive.casestudy.slimchat.service.core.utility.datetime.PrettyTimestamps;
import com.onlythenaive.casestudy.slimchat.service.core.utility.gravatar.Gravatars;

/**
 * User profile.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    private String id;
    private String email;
    private String emailHash;
    private String firstname;
    private String lastname;
    private String status;
    private Boolean connected;
    private Boolean restricted;
    private Boolean online;
    private Boolean own;
    private Boolean preview;
    private Instant createdAt;
    private Instant lastModifiedAt;
    private Instant lastSpottedAt;

    /**
     * Gets a pretty representation of profile's name.
     *
     * This method is just for presentation means of plain frontend view templates.
     * Same results should be achieved by using of custom helpers registered into the template engine.
     *
     * @return a profile's name.
     */
    @JsonIgnore
    public String getPrettyName() {
        return ProfileNames.pretty(this);
    }

    /**
     * Gets a pretty representation of profile's last spot timestamp.
     *
     * This method is just for presentation means of plain frontend view templates.
     * Same results should be achieved by using of custom helpers registered into the template engine.
     *
     * @return a profile's last spot.
     */
    @JsonIgnore
    public String getPrettyLastSpottedAt() {
        return PrettyTimestamps.pretty(this.lastSpottedAt);
    }

    /**
     * Gets a normal Gravatar URL for this profile.
     *
     * This method is just for presentation means of plain frontend view templates.
     * Same results should be achieved by using of custom helpers registered into the template engine.
     *
     * @return a normal Gravatar URL.
     */
    @JsonIgnore
    public String getGravatarUrl() {
        return Gravatars.url(this.emailHash, 64);
    }

    /**
     * Gets a large Gravatar URL for this profile.
     *
     * This method is just for presentation means of plain frontend view templates.
     * Same results should be achieved by using of custom helpers registered into the template engine.
     *
     * @return a large Gravatar URL.
     */
    @JsonIgnore
    public String getLargeGravatarUrl() {
        return Gravatars.url(this.emailHash, 256);
    }

    /**
     * Gets a small Gravatar URL for this profile.
     *
     * This method is just for presentation means of plain frontend view templates.
     * Same results should be achieved by using of custom helpers registered into the template engine.
     *
     * @return a small Gravatar URL.
     */
    @JsonIgnore
    public String getSmallGravatarUrl() {
        return Gravatars.url(this.emailHash, 32);
    }
}
