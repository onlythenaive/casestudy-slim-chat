package com.onlythenaive.casestudy.slimchat.service.core.view.login;

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
public class LoginFormData {

    private String nickname;
    private String password;
}
