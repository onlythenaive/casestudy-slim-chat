package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String accountName;
    private String accountPassword;
    private String accountPasswordDuplicate;
}
