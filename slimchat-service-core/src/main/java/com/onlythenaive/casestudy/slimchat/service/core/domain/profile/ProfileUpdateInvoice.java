package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Profile properties update invoice.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateInvoice {

    private String email;
    private String firstname;
    private String lastname;
    private String status;
    private Boolean restricted;
}
