package com.onlythenaive.casestudy.slimchat.service.core.security.password;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Security account password service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class PasswordServiceBean extends GenericComponentBean implements PasswordService {

    @Override
    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean verify(String password, String passwordHash) {
        return BCrypt.checkpw(password, passwordHash);
    }
}
