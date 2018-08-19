package com.onlythenaive.casestudy.slimchat.service.core.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Security credentials.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecurityCredentials {

    private String nickname;
    private String password;
    private String passwordDuplicate;
}
