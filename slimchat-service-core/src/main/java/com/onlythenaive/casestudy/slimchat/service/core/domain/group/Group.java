package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.time.Instant;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    private String id;
    private String caption;
    private Collection<Profile> participants;
    private Collection<Profile> moderators;
    private Instant createdAt;
}
