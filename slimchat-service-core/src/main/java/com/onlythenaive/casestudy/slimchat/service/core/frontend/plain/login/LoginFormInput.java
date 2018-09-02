package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Login form data.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginFormInput {

    private String loginKey;
    private String loginSecret;
}
