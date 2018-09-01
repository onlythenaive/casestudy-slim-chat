package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.time.Instant;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.PersistedEntity;

/**
 * Chat message entity.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "messages")
public class MessageEntity implements PersistedEntity {

    @Id
    private String id;

    @Field
    private String text;

    @Field
    private String authorId;

    @Field
    @Nullable
    private String groupId;

    @Field
    @Nullable
    private String recipientId;

    @Field
    private String chatDescriptor;

    @Field
    private Set<String> observerIds;

    @Field
    private Instant createdAt;

    @Field
    private Instant lastModifiedAt;
}
