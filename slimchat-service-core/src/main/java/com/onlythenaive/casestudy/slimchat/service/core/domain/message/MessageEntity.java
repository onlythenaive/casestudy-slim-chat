package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "messages")
public class MessageEntity {

    @Id
    private String id;

    @Field
    private String text;

    @Field
    private String authorId;

    @Field
    private boolean personal;

    @Field
    @Nullable
    private String recipientId;

    @Field
    @Nullable
    private String groupId;

    @Field
    private Instant createdAt;
}
