package com.onlythenaive.casestudy.slimchat.service.core.security;

/**
 * Security service.
 *
 * @author Ilia Gubarev
 */
public interface SecurityService {

    /**
     * Authenticates current request by the provided security token ID.
     *
     * @param tokenId the provided token ID to be used for authentication.
     */
    void authenticateByTokenId(String tokenId);
}
