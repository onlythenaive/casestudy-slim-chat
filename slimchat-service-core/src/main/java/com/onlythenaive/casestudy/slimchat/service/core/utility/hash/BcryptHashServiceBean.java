package com.onlythenaive.casestudy.slimchat.service.core.utility.hash;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Bcrypt hashing service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class BcryptHashServiceBean extends GenericComponentBean implements BcryptHashService {

    @Override
    public String hash(String text) {
        return BCrypt.hashpw(text, BCrypt.gensalt());
    }

    @Override
    public boolean verify(String text, String hash) {
        return BCrypt.checkpw(text, hash);
    }
}
