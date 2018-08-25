package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.PersistedEntity;

/**
 * Security token entity.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tokens")
public class TokenEntity implements PersistedEntity {

    @Id
    private String id;

    @Field
    private String accountId;

    @Field
    private Instant createdAt;

    @Field
    private Instant lastModifiedAt;
}
