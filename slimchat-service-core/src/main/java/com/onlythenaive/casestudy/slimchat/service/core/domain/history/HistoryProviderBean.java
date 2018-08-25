package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainComponentBean;

/**
 * Chat history provider implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class HistoryProviderBean extends DomainComponentBean implements HistoryProvider {

    @Autowired
    private HistoryAccessor historyAccessor;

    @Autowired
    private HistoryProjector historyProjector;

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public History getById(String id) {
        HistoryEntity entity = this.historyAccessor.accessById(AccessLevel.VIEW, id);
        return this.historyProjector.project(entity);
    }

    @Override
    public Collection<History> findPreviewsByOwnerId(String ownerId) {
        return this.historyRepository.findByOwnerId(ownerId).stream()
                .map(this.historyProjector::projectPreview)
                .collect(Collectors.toList());
    }
}
