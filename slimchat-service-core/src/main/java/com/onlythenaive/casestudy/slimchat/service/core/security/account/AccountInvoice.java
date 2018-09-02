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
    private String loginKey;
    private String loginSecret;
    private String email;
    private String firstname;
    private String lastname;
}
