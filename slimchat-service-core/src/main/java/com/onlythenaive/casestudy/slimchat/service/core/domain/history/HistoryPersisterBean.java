package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericPersisterBean;

/**
 * Chat history persister implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class HistoryPersisterBean extends GenericPersisterBean<HistoryEntity> implements HistoryPersister {

}
