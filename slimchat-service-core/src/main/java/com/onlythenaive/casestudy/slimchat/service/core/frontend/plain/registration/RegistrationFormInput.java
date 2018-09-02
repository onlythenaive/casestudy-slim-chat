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
    private String loginKey;
    private String loginSecret;
    private String loginSecretDuplicate;
    private String email;
    private String firstname;
    private String lastname;

    public boolean isSecretDuplicated() {
        return this.loginSecret.equals(this.loginSecretDuplicate);
    }

    public RegistrationFormInput copyWithoutSecret() {
        return RegistrationFormInput.builder()
                .id(this.id)
                .loginKey(this.loginKey)
                .email(this.email)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .build();
    }

    public AccountInvoice toInvoice() {
        return AccountInvoice.builder()
                .id(this.id)
                .loginKey(this.loginKey)
                .loginSecret(this.loginSecret)
                .email(this.email)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .build();
    }
}
