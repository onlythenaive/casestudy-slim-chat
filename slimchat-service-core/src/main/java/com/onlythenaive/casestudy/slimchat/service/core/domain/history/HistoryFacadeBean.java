package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainComponentBean;

/**
 * Chat history operation facade implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class HistoryFacadeBean extends DomainComponentBean implements HistoryFacade {

    @Autowired
    private HistoryAccessor historyAccessor;

    @Autowired
    private HistoryProvider historyProvider;

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public History get(String id) {
        return this.historyProvider.getById(id);
    }

    @Override
    public Collection<History> find() {
        return this.historyProvider.findPreviewsByOwnerId(principalId());
    }

    @Override
    public void remove(String id) {
        this.historyAccessor.accessById(AccessLevel.MANAGE, id);
        this.historyRepository.deleteById(id);
    }
}
