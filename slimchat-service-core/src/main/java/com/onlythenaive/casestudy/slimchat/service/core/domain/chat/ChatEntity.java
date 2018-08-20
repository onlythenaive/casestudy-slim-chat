package com.onlythenaive.casestudy.slimchat.service.core.domain.chat;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "chats")
public class ChatEntity {

    @Id
    private String id;

    private String caption;
    private Boolean personal;
    private Collection<String> adminIds;
    private Collection<String> participantIds;
}
