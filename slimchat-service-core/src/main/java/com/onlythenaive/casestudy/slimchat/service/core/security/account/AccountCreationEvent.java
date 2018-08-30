package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Event of account creation.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationEvent {

    private Account account;
    private String email;
    private String firstname;
    private String lastname;
}
