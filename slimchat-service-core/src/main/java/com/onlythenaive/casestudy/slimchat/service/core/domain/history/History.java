package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

/**
 * Chat history.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History {

    private String id;
    private Integer depth;
    private String threadId;
    private Group group;
    private Profile companion;
    private Collection<Message> messages;
    private Boolean preview;
}
