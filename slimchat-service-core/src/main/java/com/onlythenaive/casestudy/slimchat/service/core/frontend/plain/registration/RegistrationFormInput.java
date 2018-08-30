package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountInvoice;

/**
 * Registration form.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationFormInput {

    private String id;
    private String secret;
    private String secretDuplicate;
    private String email;
    private String firstname;
    private String lastname;

    public boolean isSecretDuplicated() {
        return this.secret.equals(this.secretDuplicate);
    }

    public AccountInvoice toInvoice() {
        return AccountInvoice.builder()
                .id(this.id)
                .secret(this.secret)
                .email(this.email)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .build();
    }
}
