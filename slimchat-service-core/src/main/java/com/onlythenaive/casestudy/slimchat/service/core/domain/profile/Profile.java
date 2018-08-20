package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String firstname;
    private String lastname;
    private String nickname;
    private Instant registeredAt;
    private Instant lastUpdatedAt;
    private Instant lastSpottedAt;
    private Boolean own;
    private Boolean online;
    private String status;
}
