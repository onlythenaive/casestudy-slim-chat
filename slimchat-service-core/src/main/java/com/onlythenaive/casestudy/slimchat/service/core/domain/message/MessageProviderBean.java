package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Chat message provider implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class MessageProviderBean extends GenericComponentBean implements MessageProvider {

    @Autowired
    private MessageProjector messageProjector;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Collection<Message> getAllByThreadId(String threadId) {
        Pageable pageable = PageRequest.of(0, 1000, Sort.Direction.DESC, "createdAt");
        return this.messageRepository.findByThreadIdAndObserverIds(threadId, principalId(), pageable).stream()
                .map(this.messageProjector::project)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Message> getAllLatestInThread() {
        return this.messageRepository.getLatestInThread(principalId()).stream()
                .map(this.messageProjector::project)
                .collect(Collectors.toList());
    }
}
