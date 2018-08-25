package com.onlythenaive.casestudy.slimchat.service.core.utility.password;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Password hash service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class PasswordHashServiceBean extends GenericComponentBean implements PasswordHashService {

    @Override
    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean verify(String password, String passwordHash) {
        return BCrypt.checkpw(password, passwordHash);
    }
}
