package com.onlythenaive.casestudy.slimchat.service.core.domain.chat;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

/**
 * Chat.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    private String id;
    private String caption;
    private Collection<Profile> admins;
    private Collection<Profile> members;
    private Collection<Message> latestMessages;
    private Boolean personal;
}
