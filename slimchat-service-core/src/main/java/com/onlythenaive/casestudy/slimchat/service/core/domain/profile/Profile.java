package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.utility.datetime.PrettyTimestamps;

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

    public String getDisplayName() {
        if (this.firstname == null) {
            return id;
        }
        return this.lastname != null ? this.firstname + " " + this.lastname : this.firstname;
    }

    public String getPrettyLastSpottedAt() {
        return PrettyTimestamps.pretty(this.lastSpottedAt);
    }
}
