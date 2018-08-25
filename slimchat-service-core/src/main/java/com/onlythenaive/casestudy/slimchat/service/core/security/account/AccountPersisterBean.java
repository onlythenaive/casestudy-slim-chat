package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericPersisterBean;

/**
 * Security account persister implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class AccountPersisterBean extends GenericPersisterBean<AccountEntity> implements AccountPersister {

}
