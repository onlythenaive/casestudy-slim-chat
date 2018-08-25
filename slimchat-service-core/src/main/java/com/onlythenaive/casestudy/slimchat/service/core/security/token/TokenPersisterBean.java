package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericPersisterBean;

/**
 * Security token persister implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class TokenPersisterBean extends GenericPersisterBean<TokenEntity> implements TokenPersister {

}
