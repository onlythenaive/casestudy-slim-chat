package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.PersistedEntity;

/**
 * Security account entity.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "accounts")
public class AccountEntity implements PersistedEntity {

    @Id
    private String id;

    @Field
    @Indexed
    private String name;

    @Field
    private String passwordHash;

    @Field
    private Instant createdAt;

    @Field
    private Instant lastModifiedAt;
}
