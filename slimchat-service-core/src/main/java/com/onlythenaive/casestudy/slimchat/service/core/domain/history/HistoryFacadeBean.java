package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;

@Service
public class HistoryFacadeBean extends GenericDomainComponentBean implements HistoryFacade {

    @Autowired
    private HistoryProjector historyProjector;

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public History getHistoryById(String id) {

        // NOTE: access with privilege checking
        HistoryEntity historyEntity = this.historyRepository.findById(id).orElseThrow(RuntimeException::new);
        if (!historyEntity.getOwnerId().equals(principalId())) {
            throw new RuntimeException();
        }

        return project(historyEntity);
    }

    @Override
    public History clearHistoryById(String id) {

        // NOTE: access with privilege checking
        HistoryEntity historyEntity = this.historyRepository.findById(id).orElseThrow(RuntimeException::new);
        if (!historyEntity.getOwnerId().equals(principalId())) {
            throw new RuntimeException();
        }

        historyEntity.setMessageIds(new ArrayList<>());
        this.historyRepository.save(historyEntity);
        return project(historyEntity);
    }

    private History project(HistoryEntity entity) {
        return this.historyProjector.intoHistory(entity);
    }
}
