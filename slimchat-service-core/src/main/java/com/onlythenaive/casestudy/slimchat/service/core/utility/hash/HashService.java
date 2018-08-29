package com.onlythenaive.casestudy.slimchat.service.core.utility.hash;

/**
 * Hashing service.
 *
 * @author Ilia Gubarev
 */
public interface HashService {

    /**
     * Generates a hash from specified text value.
     *
     * @param text a text to be processed.
     * @return the resulting hash.
     */
    String hash(String text);

    /**
     * Verifies specified text against a provided hash.
     *
     * @param text a text to be verified.
     * @param hash a text hash to be verified against.
     * @return true if verification is successful.
     */
    boolean verify(String text, String hash);
}
