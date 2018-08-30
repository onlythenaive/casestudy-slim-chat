package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account creation invoice.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInvoice {

    private String id;
    private String email;
    private String firstname;
    private String lastname;
    private String secret;
}
