package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.generic.GenericComponentBean;

/**
 * Security account service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class AccountServiceBean extends GenericComponentBean implements AccountService {

    @Override
    public Account createAccount(String nickname, String password) {
        // TODO: implement account creation
        throw new UnsupportedOperationException();
    }

    @Override
    public Account findAccountByNickname(String nickname) {
        // TODO: implement account retrieval by its nickname
        throw new UnsupportedOperationException();
    }
}
