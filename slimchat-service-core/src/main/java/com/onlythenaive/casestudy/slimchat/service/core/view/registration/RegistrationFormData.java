package com.onlythenaive.casestudy.slimchat.service.core.view.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Registration form data.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationFormData {

    private String accountName;
    private String accountPassword;
    private String accountPasswordDuplicate;
}
