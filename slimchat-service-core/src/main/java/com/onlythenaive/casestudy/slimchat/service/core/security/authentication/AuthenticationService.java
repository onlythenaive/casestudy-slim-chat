package com.onlythenaive.casestudy.slimchat.service.core.security.authentication;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.Token;

public interface AuthenticationService {

    Authentication createCurrentAuthentication(Account account, Token token);

    void removeCurrentAuthentication();
}
