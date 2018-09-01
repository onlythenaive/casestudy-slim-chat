package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageChatDescriptorBuilder;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageRepository;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

@Service
public class HistoryFacadeBean extends GenericComponentBean implements HistoryFacade {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void deleteByGroupId(String groupId) {
        String observerId = principalId();
        String chatDescriptor = new MessageChatDescriptorBuilder().groupId(groupId).build();
        removeObserverFromChat(observerId, chatDescriptor);
    }

    @Override
    public void deleteByProfileId(String profileId) {
        String observerId = principalId();
        String chatDescriptor = new MessageChatDescriptorBuilder().profileId1(profileId).profileId2(observerId).build();
        removeObserverFromChat(observerId, chatDescriptor);
    }

    private void removeObserverFromChat(String observerId, String chatDescriptor) {
        Criteria criteria = Criteria
                .where("chatDescriptor").is(chatDescriptor)
                .and("observerIds").in(observerId);
        Update update = new Update().pull("observerIds", observerId);
        this.mongoTemplate.updateMulti(Query.query(criteria), update, MessageEntity.class);
    }
}
