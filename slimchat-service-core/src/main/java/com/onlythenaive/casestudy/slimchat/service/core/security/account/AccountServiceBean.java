package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileService;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.generic.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.security.password.PasswordService;

/**
 * Security account service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class AccountServiceBean extends GenericComponentBean implements AccountService {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private ProfileService profileService;

    // TODO: replace with the real DB
    private Map<String, Account> accounts;

    @Override
    public Account createAccount(String nickname, String password) {
        Account account = retrieveAccount(nickname);
        if (account != null) {
            Map<String, String> data = new HashMap<>();
            data.put("nickname", nickname);
            throw OperationException.builder()
                    .category(ExceptionCategory.LOGIC)
                    .comment("Account already exists")
                    .data(data)
                    .textcode("x.logic.account.creation.account-already-exists")
                    .build();
        }
        return createNewAccount(nickname, password);
    }

    @Override
    public Account findAccountByNickname(String nickname) {
        return retrieveAccount(nickname);
    }

    @PostConstruct
    private void init() {
        // TODO: remove after the real DB interaction is introduced
        this.accounts = new HashMap<>();
        createNewAccount("Onlythenaive", "123");
    }

    private Account createNewAccount(String nickname, String password) {
        Account account = Account.builder()
                .id(uuid())
                .nickname(normalizedNickname(nickname))
                .passwordHash(passwordHash(password))
                .createdAt(now())
                .build();
        persistAccount(account);
        Profile profile = Profile.builder().nickname(account.getNickname()).build();
        this.profileService.createProfile(profile);
        return account;
    }

    private Account retrieveAccount(String nickname) {
        // TODO: replace with the real DB query
        return this.accounts.get(normalizedNickname(nickname));
    }

    private void persistAccount(Account account) {
        // TODO: replace with the real DB interaction
        this.accounts.put(account.getNickname(), account);
    }

    private String normalizedNickname(String nickname) {
        return nickname.toLowerCase();
    }

    private String passwordHash(String password) {
        return this.passwordService.hash(password);
    }
}
