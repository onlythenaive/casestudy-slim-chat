package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

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
 * User profile entity.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profiles")
public class ProfileEntity implements PersistedEntity {

    public static final String NAME = "profile";

    @Field
    @Id
    private String id;

    @Field
    @Nullable
    private String email;

    @Field
    @Nullable
    private String emailHash;

    @Field
    @Nullable
    private String firstname;

    @Field
    @Nullable
    private String lastname;

    @Field
    private Set<String> connectedProfileIds;

    @Field
    private Boolean restricted;

    @Field
    @Nullable
    private String status;

    @Field
    private Instant createdAt;

    @Field
    private Instant lastModifiedAt;

    @Field
    private Instant lastSpottedAt;
}
