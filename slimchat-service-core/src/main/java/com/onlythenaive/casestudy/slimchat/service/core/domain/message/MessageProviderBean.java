package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Chat message provider.
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
    public Collection<Message> findByIds(Collection<String> ids) {
        return this.messageRepository.findAllById(ids).stream()
                .map(this.messageProjector::project)
                .collect(Collectors.toList());
    }
}
