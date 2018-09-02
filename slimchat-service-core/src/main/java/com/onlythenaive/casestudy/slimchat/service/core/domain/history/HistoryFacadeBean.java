package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.chat.ChatIdWrapper;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupPreviewProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessagePersister;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfilePreviewProvider;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

@Service
public class HistoryFacadeBean extends GenericComponentBean implements HistoryFacade {

    @Autowired
    private GroupPreviewProvider groupPreviewProvider;

    @Autowired
    private MessagePersister messagePersister;

    @Autowired
    private MessageProvider messageProvider;

    @Autowired
    private ProfilePreviewProvider profilePreviewProvider;

    @Override
    public History getById(String id) {
        HistoryIdWrapper idWrapper = HistoryIdWrapper.parse(id);
        String chatId = idWrapper.getChatIdWrapper().toChatId();
        Group group = groupPreview(idWrapper.getChatIdWrapper().getGroupId());
        Profile companion = profilePreview(idWrapper.getChatIdWrapper().getCompanionId(principalId()));
        Integer depth = 0;
        Collection<Message> messages = this.messageProvider.getAllByChatId(chatId);
        return History.builder()
                .id(id)
                .chatId(chatId)
                .depth(depth)
                .group(group)
                .companion(companion)
                .messages(messages)
                .build();
    }

    @Override
    public HistorySearchResult getSearchResult(HistorySearchInvoice searchInvoice) {
        Collection<Message> messages = this.messageProvider.getAllLatestInChat();
        Collection<History> histories = messages.stream().map(this::historyFromMessage).collect(Collectors.toList());
        return HistorySearchResult.builder()
                .items(histories)
                .build();
    }

    private History historyFromMessage(Message message) {
        ChatIdWrapper chatIdWrapper = ChatIdWrapper.parse(message.getChatId());
        String companionId = chatIdWrapper.getCompanionId(principalId());
        Profile companion = profilePreview(companionId);
        HistoryIdWrapper historyIdWrapper = HistoryIdWrapper.empty().chatIdWrapper(chatIdWrapper).ownerId(principalId());
        return History.builder()
                .id(historyIdWrapper.toHistoryId())
                .depth(1)
                .chatId(message.getChatId())
                .companion(companion)
                .group(message.getGroup())
                .messages(Collections.singleton(message))
                .preview(true)
                .build();
    }

    @Override
    public void clear(String id) {
        HistoryIdWrapper idWrapper = HistoryIdWrapper.parse(id);
        String chatId = idWrapper.getChatIdWrapper().toChatId();
        Criteria criteria = Criteria
                .where("chatId").is(chatId)
                .and("observerIds").in(principalId());
        Update update = new Update().pull("observerIds", principalId());
        this.messagePersister.update(criteria, update);
    }

    private Group groupPreview(String groupId) {
        if (groupId == null) {
            return null;
        }
        return this.groupPreviewProvider.getById(groupId);
    }

    private Profile profilePreview(String profileId) {
        if (profileId == null) {
            return null;
        }
        return this.profilePreviewProvider.getById(profileId);
    }
}
