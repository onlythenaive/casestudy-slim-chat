package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.time.Instant;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Security account.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private String id;
    private Set<String> roles;
    private String secretHash;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
