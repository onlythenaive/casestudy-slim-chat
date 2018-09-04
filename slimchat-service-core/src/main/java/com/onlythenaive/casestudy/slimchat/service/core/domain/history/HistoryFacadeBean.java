package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.thread.ThreadIdWrapper;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupPreviewProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessagePersister;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfilePreviewProvider;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Chat history operations facade implementation.
 *
 * @author Ilia Gubarev
 */
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
        String threadId = idWrapper.getThreadIdWrapper().toThreadId();
        Group group = groupPreview(idWrapper.getThreadIdWrapper().getGroupId());
        Profile companion = profilePreview(idWrapper.getThreadIdWrapper().getCompanionId(principalId()));
        Integer depth = 0;
        Collection<Message> messages = this.messageProvider.getAllByThreadId(threadId);
        return History.builder()
                .id(id)
                .threadId(threadId)
                .depth(depth)
                .group(group)
                .companion(companion)
                .messages(messages)
                .build();
    }

    @Override
    public HistorySearchResult getSearchResult(HistorySearchInvoice searchInvoice) {
        Collection<Message> messages = this.messageProvider.getAllLatestInThread();
        Collection<History> histories = messages.stream().map(this::historyFromMessage).collect(Collectors.toList());
        return HistorySearchResult.builder()
                .items(histories)
                .build();
    }

    private History historyFromMessage(Message message) {
        ThreadIdWrapper threadIdWrapper = ThreadIdWrapper.parse(message.getThreadId());
        String companionId = threadIdWrapper.getCompanionId(principalId());
        Profile companion = profilePreview(companionId);
        HistoryIdWrapper historyIdWrapper = HistoryIdWrapper.empty().threadIdWrapper(threadIdWrapper).ownerId(principalId());
        return History.builder()
                .id(historyIdWrapper.toHistoryId())
                .depth(1)
                .threadId(message.getThreadId())
                .companion(companion)
                .group(message.getGroup())
                .messages(Collections.singleton(message))
                .preview(true)
                .build();
    }

    @Override
    public void clear(String id) {
        HistoryIdWrapper idWrapper = HistoryIdWrapper.parse(id);
        String threadId = idWrapper.getThreadIdWrapper().toThreadId();
        Criteria criteria = Criteria
                .where("threadId").is(threadId)
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
