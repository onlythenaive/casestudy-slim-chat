package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

/**
 * Connection proposal.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {

    private String id;
    private String text;
    private Profile initiator;
    private Profile acceptor;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
