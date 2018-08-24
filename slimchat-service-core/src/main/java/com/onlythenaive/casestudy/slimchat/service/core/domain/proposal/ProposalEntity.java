package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainEntity;

/**
 * Connection proposal entity.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "proposals")
public class ProposalEntity implements DomainEntity {

    @Id
    private String id;

    @Field
    @Nullable
    private String text;

    @Field
    private String initiatorId;

    @Field
    private String acceptorId;

    @Field
    private Instant createdAt;

    @Field
    private Instant lastModifiedAt;
}
