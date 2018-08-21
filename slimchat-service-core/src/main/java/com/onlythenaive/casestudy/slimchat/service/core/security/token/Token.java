package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Security token for incoming request authentication.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    private String id;
    private String accountId;
    private Instant createdAt;
}
