package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History {

    private String id;
    private Profile owner;
    private boolean personal;
    private Profile referencedUser;
    private Group referencedGroup;
    private Collection<Message> messages;
}
