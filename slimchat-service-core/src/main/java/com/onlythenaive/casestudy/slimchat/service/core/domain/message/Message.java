package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.domain.chat.Chat;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

/**
 * Chat message.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String id;
    private String text;
    private Chat chat;
    private Profile author;
    private Instant createdAt;
}
