package com.onlythenaive.casestudy.slimchat.service.core.utility.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * MD-5 hashing service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class Md5HashServiceBean extends GenericComponentBean implements Md5HashService {

    @Override
    public String hash(String text) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.update(text.getBytes());
            byte[] bytes = algorithm.digest();
            return DatatypeConverter.printHexBinary(bytes).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw wrappedException(e);
        }
    }

    @Override
    public boolean verify(String text, String hash) {
        // TODO: implement this method
        throw notSupported();
    }
}
